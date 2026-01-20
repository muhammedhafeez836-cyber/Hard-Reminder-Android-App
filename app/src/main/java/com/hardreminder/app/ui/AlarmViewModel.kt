package com.hardreminder.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hardreminder.app.HardReminderApp
import com.hardreminder.app.alarm.AlarmRingerService
import com.hardreminder.app.data.ReminderEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AlarmViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = (application as HardReminderApp).container.reminderRepository
    private val alarmScheduler = (application as HardReminderApp).container.alarmScheduler

    val ringing: StateFlow<List<ReminderEntity>> = repo.observeRinging()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun snooze(reminderId: Long, minutes: Int) {
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            val nextAt = now + minutes * 60_000L
            repo.snoozeReminder(reminderId, nextAt, now)
            alarmScheduler.scheduleAlarm(reminderId, nextAt)
            AlarmRingerService.requestRefresh(getApplication())
            AlarmRingerService.requestStopIfNoRinging(getApplication())
        }
    }
}
