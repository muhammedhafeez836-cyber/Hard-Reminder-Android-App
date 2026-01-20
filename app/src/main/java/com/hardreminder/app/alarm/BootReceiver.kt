package com.hardreminder.app.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hardreminder.app.HardReminderApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val pendingResult = goAsync()
        val app = context.applicationContext as HardReminderApp
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val reminders = app.container.reminderRepository.getAllReminders()
                reminders.forEach { reminder ->
                    app.container.alarmScheduler.scheduleAlarm(reminder.id, reminder.scheduledAtMillis)
                }
            } finally {
                pendingResult.finish()
            }
        }
    }
}
