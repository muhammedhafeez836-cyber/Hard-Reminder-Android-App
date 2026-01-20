package com.hardreminder.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hardreminder.app.HardReminderApp
import com.hardreminder.app.data.ReminderEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AddEditUiState(
    val title: String = "",
    val details: String = "",
    val scheduledAtMillis: Long? = null,
    val isEditing: Boolean = false,
    val isLoaded: Boolean = false
)

class AddEditViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = (application as HardReminderApp).container.reminderRepository
    private val alarmScheduler = (application as HardReminderApp).container.alarmScheduler

    private val _uiState = MutableStateFlow(AddEditUiState())
    val uiState: StateFlow<AddEditUiState> = _uiState

    fun loadReminder(reminderId: Long) {
        if (reminderId <= 0L || _uiState.value.isLoaded) return
        viewModelScope.launch {
            val reminder = repo.getReminder(reminderId) ?: return@launch
            _uiState.value = AddEditUiState(
                title = reminder.title,
                details = reminder.details,
                scheduledAtMillis = reminder.scheduledAtMillis,
                isEditing = true,
                isLoaded = true
            )
        }
    }

    fun updateTitle(value: String) {
        _uiState.value = _uiState.value.copy(title = value)
    }

    fun updateDetails(value: String) {
        _uiState.value = _uiState.value.copy(details = value)
    }

    fun updateScheduledAt(millis: Long) {
        _uiState.value = _uiState.value.copy(scheduledAtMillis = millis)
    }

    fun saveReminder(reminderId: Long?, onDone: () -> Unit) {
        val state = _uiState.value
        if (state.title.isBlank() || state.scheduledAtMillis == null) return
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            val scheduledAt = state.scheduledAtMillis
            val id = if (reminderId != null && reminderId > 0L) {
                val existing = repo.getReminder(reminderId)
                if (existing != null) {
                    val updated = existing.copy(
                        title = state.title.trim(),
                        details = state.details.trim(),
                        scheduledAtMillis = scheduledAt,
                        updatedAtMillis = now
                    )
                    repo.updateReminder(updated)
                    reminderId
                } else {
                    repo.createReminder(state.title.trim(), state.details.trim(), scheduledAt, now)
                }
            } else {
                repo.createReminder(state.title.trim(), state.details.trim(), scheduledAt, now)
            }
            alarmScheduler.scheduleAlarm(id, scheduledAt)
            onDone()
        }
    }

    fun canScheduleExactAlarms(): Boolean = alarmScheduler.canScheduleExactAlarms()
}
