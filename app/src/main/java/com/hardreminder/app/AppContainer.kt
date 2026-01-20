package com.hardreminder.app

import android.content.Context
import com.hardreminder.app.alarm.AlarmScheduler
import com.hardreminder.app.data.AppDatabase
import com.hardreminder.app.data.ReminderRepository

class AppContainer(context: Context) {
    private val database = AppDatabase.getInstance(context)

    val reminderRepository = ReminderRepository(database.reminderDao())
    val alarmScheduler = AlarmScheduler(context)
}
