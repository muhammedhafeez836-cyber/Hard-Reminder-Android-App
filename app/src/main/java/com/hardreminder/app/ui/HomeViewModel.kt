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
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId

enum class CalendarViewMode { MONTH, WEEK, DAY, ALL }

data class CalendarUiState(
    val viewMode: CalendarViewMode = CalendarViewMode.MONTH,
    val selectedDate: LocalDate = LocalDate.now(),
    val monthAnchor: LocalDate = LocalDate.now().withDayOfMonth(1)
)

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = (application as HardReminderApp).container.reminderRepository
    private val alarmScheduler = (application as HardReminderApp).container.alarmScheduler

    private val uiState = MutableStateFlow(CalendarUiState())
    val calendarState: StateFlow<CalendarUiState> = uiState

    val reminders: StateFlow<List<ReminderEntity>> = uiState
        .flatMapLatest { state ->
            when (state.viewMode) {
                CalendarViewMode.MONTH, CalendarViewMode.WEEK, CalendarViewMode.DAY -> {
                    val range = computeRange(state)
                    repo.observeBetween(range.first, range.second)
                }
                CalendarViewMode.ALL -> repo.observeAll()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun setViewMode(mode: CalendarViewMode) {
        uiState.value = uiState.value.copy(viewMode = mode)
    }

    fun selectDate(date: LocalDate) {
        uiState.value = uiState.value.copy(
            selectedDate = date,
            monthAnchor = date.withDayOfMonth(1)
        )
    }

    fun goPrevious() {
        val state = uiState.value
        val updated = when (state.viewMode) {
            CalendarViewMode.MONTH -> state.monthAnchor.minusMonths(1)
            CalendarViewMode.WEEK -> state.selectedDate.minusWeeks(1)
            CalendarViewMode.DAY -> state.selectedDate.minusDays(1)
            CalendarViewMode.ALL -> state.selectedDate
        }
        uiState.value = state.copy(
            selectedDate = updated,
            monthAnchor = updated.withDayOfMonth(1)
        )
    }

    fun goNext() {
        val state = uiState.value
        val updated = when (state.viewMode) {
            CalendarViewMode.MONTH -> state.monthAnchor.plusMonths(1)
            CalendarViewMode.WEEK -> state.selectedDate.plusWeeks(1)
            CalendarViewMode.DAY -> state.selectedDate.plusDays(1)
            CalendarViewMode.ALL -> state.selectedDate
        }
        uiState.value = state.copy(
            selectedDate = updated,
            monthAnchor = updated.withDayOfMonth(1)
        )
    }

    private fun computeRange(state: CalendarUiState): Pair<Long, Long> {
        val zone = ZoneId.systemDefault()
        return when (state.viewMode) {
            CalendarViewMode.MONTH -> {
                val start = state.monthAnchor.withDayOfMonth(1)
                val end = state.monthAnchor.withDayOfMonth(state.monthAnchor.lengthOfMonth())
                startOfDayMillis(start, zone) to endOfDayMillis(end, zone)
            }
            CalendarViewMode.WEEK -> {
                val start = startOfWeek(state.selectedDate)
                val end = start.plusDays(6)
                startOfDayMillis(start, zone) to endOfDayMillis(end, zone)
            }
            CalendarViewMode.DAY -> {
                startOfDayMillis(state.selectedDate, zone) to endOfDayMillis(state.selectedDate, zone)
            }
            CalendarViewMode.ALL -> {
                0L to Long.MAX_VALUE
            }
        }
    }

    private fun startOfWeek(date: LocalDate): LocalDate {
        val diff = (date.dayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7
        return date.minusDays(diff.toLong())
    }

    private fun startOfDayMillis(date: LocalDate, zone: ZoneId): Long {
        return date.atStartOfDay(zone).toInstant().toEpochMilli()
    }

    private fun endOfDayMillis(date: LocalDate, zone: ZoneId): Long {
        return date.plusDays(1).atStartOfDay(zone).toInstant().toEpochMilli() - 1
    }

    fun canScheduleExactAlarms(): Boolean = alarmScheduler.canScheduleExactAlarms()

    fun isIgnoringBatteryOptimizations(): Boolean {
        val powerManager = getApplication<Application>()
            .getSystemService(PowerManager::class.java)
        return powerManager.isIgnoringBatteryOptimizations(getApplication<Application>().packageName)
    }
}
