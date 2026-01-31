package com.hardreminder.app.ui

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Brush
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    viewModel: HomeViewModel,
    onAdd: () -> Unit,
    onDetail: (Long) -> Unit,
    onBattery: () -> Unit,
    onSettings: () -> Unit
) {
    val reminders = viewModel.reminders.collectAsState().value
    val calendarState = viewModel.calendarState.collectAsState().value
    val dateFormatter = rememberDateTimeFormatter()
    val context = LocalContext.current
    val zone = ZoneId.systemDefault()

    val remindersByDate = rememberGroupedByDate(reminders, zone)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hard Reminder") },
                actions = {
                    IconButton(onClick = onSettings) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Filled.Add, contentDescription = "Add reminder")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (!viewModel.canScheduleExactAlarms() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    WarningCard(
                        text = "Exact alarms are disabled. Reminders may be late.",
                        actionText = "Enable",
                        onAction = {
                            context.startActivity(Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM))
                        }
                    )
                }

                if (!viewModel.isIgnoringBatteryOptimizations()) {
                    WarningCard(
                        text = "Battery optimizations may silence alarms.",
                        actionText = "Fix",
                        onAction = onBattery
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ViewToggle(
                        label = "Month",
                        selected = calendarState.viewMode == CalendarViewMode.MONTH,
                        onClick = { viewModel.setViewMode(CalendarViewMode.MONTH) }
                    )
                    ViewToggle(
                        label = "Week",
                        selected = calendarState.viewMode == CalendarViewMode.WEEK,
                        onClick = { viewModel.setViewMode(CalendarViewMode.WEEK) }
                    )
                    ViewToggle(
                        label = "Day",
                        selected = calendarState.viewMode == CalendarViewMode.DAY,
                        onClick = { viewModel.setViewMode(CalendarViewMode.DAY) }
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ViewToggle(
                        label = "All reminders",
                        selected = calendarState.viewMode == CalendarViewMode.ALL,
                        onClick = { viewModel.setViewMode(CalendarViewMode.ALL) }
                    )
                }

                CalendarHeader(
                    state = calendarState,
                    onPrevious = viewModel::goPrevious,
                    onNext = viewModel::goNext
                )

                when (calendarState.viewMode) {
                    CalendarViewMode.MONTH -> {
                        LazyColumn(
                            contentPadding = PaddingValues(bottom = 80.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            item {
                                MonthGrid(
                                    monthAnchor = calendarState.monthAnchor,
                                    selectedDate = calendarState.selectedDate,
                                    remindersByDate = remindersByDate,
                                    onSelectDate = { date ->
                                        viewModel.selectDate(date)
                                        viewModel.setViewMode(CalendarViewMode.DAY)
                                    }
                                )
                            }
                            item {
                                DayAgenda(
                                    selectedDate = calendarState.selectedDate,
                                    reminders = remindersByDate[calendarState.selectedDate].orEmpty(),
                                    dateFormatter = dateFormatter,
                                    onDetail = onDetail
                                )
                            }
                        }
                    }
                    CalendarViewMode.WEEK -> {
                        WeekAgenda(
                            selectedDate = calendarState.selectedDate,
                            remindersByDate = remindersByDate,
                            dateFormatter = dateFormatter,
                            onSelectDate = { date ->
                                viewModel.selectDate(date)
                                viewModel.setViewMode(CalendarViewMode.DAY)
                            },
                            onDetail = onDetail
                        )
                    }
                    CalendarViewMode.DAY -> {
                        DayAgenda(
                            selectedDate = calendarState.selectedDate,
                            reminders = remindersByDate[calendarState.selectedDate].orEmpty(),
                            dateFormatter = dateFormatter,
                            onDetail = onDetail
                        )
                    }
                    CalendarViewMode.ALL -> {
                        AllRemindersAgenda(
                            reminders = reminders,
                            dateFormatter = dateFormatter,
                            onDetail = onDetail
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CalendarHeader(
    state: CalendarUiState,
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    val formatter = when (state.viewMode) {
        CalendarViewMode.MONTH -> DateTimeFormatter.ofPattern("MMMM yyyy")
        CalendarViewMode.WEEK -> DateTimeFormatter.ofPattern("MMM d")
        CalendarViewMode.DAY -> DateTimeFormatter.ofPattern("EEEE, MMM d")
        CalendarViewMode.ALL -> DateTimeFormatter.ofPattern("MMM d")
    }
    val label = when (state.viewMode) {
        CalendarViewMode.MONTH -> state.monthAnchor.format(formatter)
        CalendarViewMode.WEEK -> {
            val start = startOfWeek(state.selectedDate)
            val end = start.plusDays(6)
            "${start.format(formatter)} - ${end.format(formatter)}"
        }
        CalendarViewMode.DAY -> state.selectedDate.format(formatter)
        CalendarViewMode.ALL -> "All reminders"
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (state.viewMode == CalendarViewMode.ALL) {
            Spacer(modifier = Modifier.width(48.dp))
        } else {
            IconButton(onClick = onPrevious) {
                Icon(Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = "Previous")
            }
        }
        Text(label, style = MaterialTheme.typography.titleMedium)
        if (state.viewMode == CalendarViewMode.ALL) {
            Spacer(modifier = Modifier.width(48.dp))
        } else {
            IconButton(onClick = onNext) {
                Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = "Next")
            }
        }
    }
}

@Composable
private fun MonthGrid(
    monthAnchor: LocalDate,
    selectedDate: LocalDate,
    remindersByDate: Map<LocalDate, List<com.hardreminder.app.data.ReminderEntity>>,
    onSelectDate: (LocalDate) -> Unit
) {
    val daysOfWeek = DayOfWeek.values().toList()
    Row(modifier = Modifier.fillMaxWidth()) {
        daysOfWeek.forEach { day ->
            Text(
                text = day.name.take(3),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }

    val cells = buildMonthCells(monthAnchor)
    cells.chunked(7).forEach { week ->
        Row(modifier = Modifier.fillMaxWidth()) {
            week.forEach { date ->
                if (date == null) {
                    Spacer(modifier = Modifier.weight(1f).aspectRatio(1f).padding(2.dp))
                } else {
                    val previews = remindersByDate[date].orEmpty()
                        .take(2)
                        .map { previewTitle(it.title) }
                    val isSelected = date == selectedDate
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(2.dp)
                            .clickable { onSelectDate(date) },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) {
                                MaterialTheme.colorScheme.primaryContainer
                            } else {
                                MaterialTheme.colorScheme.surface
                            }
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(6.dp)) {
                            Text(text = date.dayOfMonth.toString(), style = MaterialTheme.typography.labelMedium)
                            previews.forEach { title ->
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.bodySmall,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WeekAgenda(
    selectedDate: LocalDate,
    remindersByDate: Map<LocalDate, List<com.hardreminder.app.data.ReminderEntity>>,
    dateFormatter: SimpleDateFormat,
    onSelectDate: (LocalDate) -> Unit,
    onDetail: (Long) -> Unit
) {
    val weekStart = startOfWeek(selectedDate)
    val days = (0..6).map { weekStart.plusDays(it.toLong()) }
    LazyColumn(
        contentPadding = PaddingValues(bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(days) { date ->
            val dayReminders = remindersByDate[date].orEmpty()
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = date.format(DateTimeFormatter.ofPattern("EEE, MMM d")),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.clickable { onSelectDate(date) }
                )
                if (dayReminders.isEmpty()) {
                    Text("No reminders.")
                } else {
                    dayReminders.forEach { reminder ->
                        ReminderCard(reminder, dateFormatter, onDetail)
                    }
                }
            }
        }
    }
}

@Composable
private fun DayAgenda(
    selectedDate: LocalDate,
    reminders: List<com.hardreminder.app.data.ReminderEntity>,
    dateFormatter: SimpleDateFormat,
    onDetail: (Long) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = selectedDate.format(DateTimeFormatter.ofPattern("EEEE, MMM d")),
            style = MaterialTheme.typography.titleMedium
        )
        if (reminders.isEmpty()) {
            Text("No reminders for this day.")
        } else {
            reminders.forEach { reminder ->
                ReminderCard(reminder, dateFormatter, onDetail)
            }
        }
    }
}

@Composable
private fun AllRemindersAgenda(
    reminders: List<com.hardreminder.app.data.ReminderEntity>,
    dateFormatter: SimpleDateFormat,
    onDetail: (Long) -> Unit
) {
    if (reminders.isEmpty()) {
        Text("No reminders.")
    } else {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(reminders) { reminder ->
                ReminderCard(reminder, dateFormatter, onDetail)
            }
        }
    }
}

@Composable
private fun ReminderCard(
    reminder: com.hardreminder.app.data.ReminderEntity,
    dateFormatter: SimpleDateFormat,
    onDetail: (Long) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDetail(reminder.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(reminder.title, style = MaterialTheme.typography.titleMedium)
            if (reminder.details.isNotBlank()) {
                Text(reminder.details, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                dateFormatter.format(Date(reminder.scheduledAtMillis)),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun WarningCard(
    text: String,
    actionText: String,
    onAction: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Warning, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text, style = MaterialTheme.typography.bodyMedium)
            }
            FilledTonalButton(onClick = onAction) {
                Text(actionText)
            }
        }
    }
}

@Composable
private fun ViewToggle(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val colors = if (selected) {
        ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    } else {
        ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
    FilledTonalButton(onClick = onClick, colors = colors) {
        Text(label, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BatteryOptimizationScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Battery Optimization") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Disable battery optimizations for Hard Reminder to ensure alarms fire on time.",
                style = MaterialTheme.typography.bodyMedium
            )
            Button(onClick = {
                val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
                context.startActivity(intent)
            }) {
                Text("Open Battery Settings")
            }
        }
    }
}

@Composable
private fun rememberDateTimeFormatter(): SimpleDateFormat {
    return SimpleDateFormat("EEE, MMM d - h:mm a", Locale.getDefault())
}

private fun rememberGroupedByDate(
    reminders: List<com.hardreminder.app.data.ReminderEntity>,
    zoneId: ZoneId
): Map<LocalDate, List<com.hardreminder.app.data.ReminderEntity>> {
    return reminders.groupBy { reminder ->
        LocalDate.ofInstant(
            java.time.Instant.ofEpochMilli(reminder.scheduledAtMillis),
            zoneId
        )
    }
}

private fun buildMonthCells(monthAnchor: LocalDate): List<LocalDate?> {
    val firstOfMonth = monthAnchor.withDayOfMonth(1)
    val leading = (firstOfMonth.dayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7
    val totalDays = monthAnchor.lengthOfMonth()
    val cells = mutableListOf<LocalDate?>()
    repeat(leading) { cells.add(null) }
    for (day in 1..totalDays) {
        cells.add(monthAnchor.withDayOfMonth(day))
    }
    while (cells.size % 7 != 0) {
        cells.add(null)
    }
    return cells
}

private fun previewTitle(title: String): String {
    val words = title.trim().split(" ")
    val preview = words.take(3).joinToString(" ")
    return if (preview.length > 18) preview.take(18) else preview
}

private fun startOfWeek(date: LocalDate): LocalDate {
    val diff = (date.dayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7
    return date.minusDays(diff.toLong())
}
