package com.hardreminder.app.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != ACTION_ALARM_TRIGGERED) return
        val serviceIntent = Intent(context, AlarmRingerService::class.java).apply {
            action = AlarmRingerService.ACTION_ALARM_TRIGGERED
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }

    companion object {
        const val ACTION_ALARM_TRIGGERED = "com.hardreminder.app.ACTION_ALARM_TRIGGERED"
        const val EXTRA_REMINDER_ID = "extra_reminder_id"
    }
}
