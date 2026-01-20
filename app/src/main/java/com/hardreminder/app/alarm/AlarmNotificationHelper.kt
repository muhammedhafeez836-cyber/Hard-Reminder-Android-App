package com.hardreminder.app.alarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.hardreminder.app.R
import com.hardreminder.app.ui.AlarmActivity

object AlarmNotificationHelper {
    const val CHANNEL_ID = "hard_reminder_alarm"

    fun ensureChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(NotificationManager::class.java)
            val channel = NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.channel_alarm_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = context.getString(R.string.channel_alarm_description)
                setSound(null, null)
            }
            manager.createNotificationChannel(channel)
        }
    }

    fun buildNotification(context: Context, titles: List<String>): Notification {
        val reminderCount = titles.size
        val alarmIntent = Intent(context, AlarmActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(AlarmActivity.EXTRA_OPEN_SNOOZE_PICKER, reminderCount == 1)
        }
        val fullScreenIntent = PendingIntent.getActivity(
            context,
            0,
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val snoozeOptions = SnoozeSettingsStore.getOptions(context)

        val title = if (reminderCount > 1) {
            "$reminderCount reminders due"
        } else {
            "Reminder due"
        }
        val contentText = buildContentText(titles)

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(contentText)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true)
            .setAutoCancel(false)
            .setFullScreenIntent(fullScreenIntent, true)
            .addAction(0, snoozeOptions[0].label, createSnoozeAction(context, snoozeOptions[0].minutes, 101))
            .addAction(0, snoozeOptions[1].label, createSnoozeAction(context, snoozeOptions[1].minutes, 102))
            .addAction(0, snoozeOptions[2].label, createSnoozeAction(context, snoozeOptions[2].minutes, 103))
            .build()
    }

    private fun createSnoozeAction(context: Context, minutes: Int, requestCode: Int): PendingIntent {
        val snoozeIntent = Intent(context, SnoozeReceiver::class.java).apply {
            action = SnoozeReceiver.ACTION_SNOOZE_ALL
            putExtra(SnoozeReceiver.EXTRA_SNOOZE_MINUTES, minutes)
        }
        return PendingIntent.getBroadcast(
            context,
            requestCode,
            snoozeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun buildContentText(titles: List<String>): String {
        if (titles.isEmpty()) return "Open to snooze"
        val shown = titles.take(3)
        val extra = titles.size - shown.size
        val base = shown.joinToString(", ")
        return if (extra > 0) "$base +$extra more" else base
    }
}
