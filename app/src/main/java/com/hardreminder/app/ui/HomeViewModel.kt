package com.hardreminder.app.ui

import android.app.Application
import android.os.PowerManager
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

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = (application as HardReminderApp).container.reminderRepository
    private val alarmScheduler = (application as HardReminderApp).container.alarmScheduler

    private val nowFlow = MutableStateFlow(System.currentTimeMillis())

    val upcoming: StateFlow<List<ReminderEntity>> = nowFlow
        .flatMapLatest { now -> repo.observeUpcoming(now) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun refreshNow() {
        nowFlow.value = System.currentTimeMillis()
    }

    fun canScheduleExactAlarms(): Boolean = alarmScheduler.canScheduleExactAlarms()

    fun isIgnoringBatteryOptimizations(): Boolean {
        val powerManager = getApplication<Application>()
            .getSystemService(PowerManager::class.java)
        return powerManager.isIgnoringBatteryOptimizations(getApplication<Application>().packageName)
    }
}
