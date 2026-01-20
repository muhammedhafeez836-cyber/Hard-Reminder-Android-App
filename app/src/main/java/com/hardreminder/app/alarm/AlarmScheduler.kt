package com.hardreminder.app.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.hardreminder.app.ui.MainActivity

class AlarmScheduler(private val context: Context) {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    fun canScheduleExactAlarms(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager.canScheduleExactAlarms()
        } else {
            true
        }
    }

    fun scheduleAlarm(reminderId: Long, triggerAtMillis: Long) {
        val operation = createAlarmPendingIntent(reminderId)
        val showIntent = PendingIntent.getActivity(
            context,
            0,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmClockInfo = AlarmManager.AlarmClockInfo(triggerAtMillis, showIntent)
        alarmManager.setAlarmClock(alarmClockInfo, operation)
    }

    fun cancelAlarm(reminderId: Long) {
        alarmManager.cancel(createAlarmPendingIntent(reminderId))
    }

    private fun createAlarmPendingIntent(reminderId: Long): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            action = AlarmReceiver.ACTION_ALARM_TRIGGERED
            putExtra(AlarmReceiver.EXTRA_REMINDER_ID, reminderId)
        }
        return PendingIntent.getBroadcast(
            context,
            reminderId.toInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}
