package com.hardreminder.app.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hardreminder.app.HardReminderApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SnoozeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != ACTION_SNOOZE_ALL) return
        val minutes = intent.getIntExtra(EXTRA_SNOOZE_MINUTES, 0)
        if (minutes <= 0) return
        val pendingResult = goAsync()
        val app = context.applicationContext as HardReminderApp
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val now = System.currentTimeMillis()
                val nextAt = now + minutes * 60_000L
                val ringing = app.container.reminderRepository.getRingingReminders()
                ringing.forEach { reminder ->
                    app.container.reminderRepository.snoozeReminder(reminder.id, nextAt, now)
                    app.container.alarmScheduler.scheduleAlarm(reminder.id, nextAt)
                }
                AlarmRingerService.requestRefresh(context)
                AlarmRingerService.requestStopIfNoRinging(context)
            } finally {
                pendingResult.finish()
            }
        }
    }

    companion object {
        const val ACTION_SNOOZE_ALL = "com.hardreminder.app.ACTION_SNOOZE_ALL"
        const val EXTRA_SNOOZE_MINUTES = "extra_snooze_minutes"
    }
}
