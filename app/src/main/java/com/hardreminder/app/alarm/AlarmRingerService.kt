package com.hardreminder.app.alarm

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.IBinder
import android.os.PowerManager
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.core.content.getSystemService
import com.hardreminder.app.HardReminderApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AlarmRingerService : Service() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var mediaPlayer: MediaPlayer? = null
    private val playbackLock = Any()
    @Volatile private var isStarting = false
    private var wakeLock: PowerManager.WakeLock? = null
    private var vibrator: Vibrator? = null
    private var screenReceiverRegistered = false
    private val screenReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (Intent.ACTION_SCREEN_OFF == intent.action) {
                stopAlarmAudioOnly()
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        AlarmNotificationHelper.ensureChannel(this)
        registerScreenReceiver()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_ALARM_TRIGGERED -> {
                serviceScope.launch { handleAlarmTriggered() }
            }
            ACTION_SILENCE_AUDIO -> {
                serviceScope.launch { stopAlarmAudioOnly() }
            }
            ACTION_STOP_IF_NO_RINGING -> {
                serviceScope.launch { stopIfNoRinging() }
            }
            ACTION_REFRESH_NOTIFICATION -> {
                serviceScope.launch { refreshNotification() }
            }
        }
        return START_STICKY
    }

    private suspend fun handleAlarmTriggered() {
        val app = application as HardReminderApp
        val now = System.currentTimeMillis()
        app.container.reminderRepository.markDueAsRinging(now)
        val ringing = app.container.reminderRepository.getRingingReminders()
        if (ringing.isNotEmpty()) {
            startAlarmPlayback()
            val notification = AlarmNotificationHelper.buildNotification(
                this,
                ringing.map { it.title }
            )
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    private suspend fun refreshNotification() {
        val app = application as HardReminderApp
        val ringing = app.container.reminderRepository.getRingingReminders()
        if (ringing.isNotEmpty()) {
            val notification = AlarmNotificationHelper.buildNotification(
                this,
                ringing.map { it.title }
            )
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    private suspend fun stopIfNoRinging() {
        val app = application as HardReminderApp
        val count = app.container.reminderRepository.getRingingCount()
        if (count == 0) {
            stopAlarmPlayback()
            stopForeground(STOP_FOREGROUND_REMOVE)
            stopSelf()
        } else {
            refreshNotification()
        }
    }

    private fun startAlarmPlayback() {
        synchronized(playbackLock) {
            if (mediaPlayer?.isPlaying == true || isStarting) return
            isStarting = true
        }
        val storedUri = AlarmToneStore.getToneUri(this)
        val fallbackUri = AlarmToneStore.getFallbackUri()
        val alarmUri = storedUri ?: fallbackUri

        val player = createPlayer(alarmUri) ?: if (storedUri != null) createPlayer(fallbackUri) else null
        synchronized(playbackLock) {
            mediaPlayer = player
            isStarting = false
        }

        ensureWakeLock()
        startVibrationIfEnabled()
    }

    private fun createPlayer(uri: android.net.Uri): MediaPlayer? {
        return try {
            MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                setDataSource(applicationContext, uri)
                isLooping = true
                prepare()
                start()
            }
        } catch (_: Exception) {
            null
        }
    }

    private fun stopAlarmPlayback() {
        synchronized(playbackLock) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            isStarting = false
        }
        vibrator?.cancel()
        vibrator = null
        wakeLock?.let { lock ->
            if (lock.isHeld) {
                lock.release()
            }
        }
        wakeLock = null
    }

    private fun stopAlarmAudioOnly() {
        synchronized(playbackLock) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            isStarting = false
        }
        startVibrationIfEnabled()
    }

    private fun ensureWakeLock() {
        if (wakeLock?.isHeld == true) return
        val powerManager = getSystemService<PowerManager>()
        wakeLock = powerManager?.newWakeLock(
            PowerManager.PARTIAL_WAKE_LOCK,
            "HardReminder:AlarmWakelock"
        )?.apply {
            acquire(10 * 60 * 1000L)
        }
    }

    private fun startVibrationIfEnabled() {
        if (!AlarmVibrationStore.isEnabled(this)) {
            vibrator?.cancel()
            vibrator = null
            return
        }
        vibrator = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            val manager = getSystemService<VibratorManager>()
            manager?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        val pattern = longArrayOf(0, 1000, 1000)
        vibrator?.vibrate(VibrationEffect.createWaveform(pattern, 0))
    }

    private fun registerScreenReceiver() {
        if (screenReceiverRegistered) return
        val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
        registerReceiver(screenReceiver, filter)
        screenReceiverRegistered = true
    }

    private fun unregisterScreenReceiver() {
        if (!screenReceiverRegistered) return
        unregisterReceiver(screenReceiver)
        screenReceiverRegistered = false
    }

    override fun onDestroy() {
        stopAlarmPlayback()
        unregisterScreenReceiver()
        super.onDestroy()
    }

    companion object {
        const val ACTION_ALARM_TRIGGERED = "com.hardreminder.app.ACTION_ALARM_TRIGGERED"
        const val ACTION_SILENCE_AUDIO = "com.hardreminder.app.ACTION_SILENCE_AUDIO"
        const val ACTION_STOP_IF_NO_RINGING = "com.hardreminder.app.ACTION_STOP_IF_NO_RINGING"
        const val ACTION_REFRESH_NOTIFICATION = "com.hardreminder.app.ACTION_REFRESH_NOTIFICATION"
        const val NOTIFICATION_ID = 1001

        fun requestSilenceAudio(context: android.content.Context) {
            val intent = Intent(context, AlarmRingerService::class.java).apply {
                action = ACTION_SILENCE_AUDIO
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        fun requestStopIfNoRinging(context: android.content.Context) {
            val intent = Intent(context, AlarmRingerService::class.java).apply {
                action = ACTION_STOP_IF_NO_RINGING
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        fun requestRefresh(context: android.content.Context) {
            val intent = Intent(context, AlarmRingerService::class.java).apply {
                action = ACTION_REFRESH_NOTIFICATION
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }
    }
}
