package com.hardreminder.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hardreminder.app.HardReminderApp
import com.hardreminder.app.data.ReminderEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ReminderDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = (application as HardReminderApp).container.reminderRepository
    private val alarmScheduler = (application as HardReminderApp).container.alarmScheduler

    private val reminderIdFlow = MutableStateFlow<Long?>(null)

    val reminder: StateFlow<ReminderEntity?> = reminderIdFlow
        .flatMapLatest { id ->
            if (id == null) {
                kotlinx.coroutines.flow.flowOf(null)
            } else {
                repo.observeReminder(id)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    fun loadReminder(reminderId: Long) {
        reminderIdFlow.value = reminderId
    }

    fun deleteReminder(onDone: () -> Unit) {
        val current = reminder.value ?: return
        viewModelScope.launch {
            repo.deleteReminder(current)
            alarmScheduler.cancelAlarm(current.id)
            onDone()
        }
    }
}
