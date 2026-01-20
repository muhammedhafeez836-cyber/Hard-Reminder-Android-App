package com.hardreminder.app.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddEditReminderScreen(
    viewModel: AddEditViewModel,
    reminderId: Long? = null,
    onSaved: () -> Unit,
    onCancel: () -> Unit
) {
    val state = viewModel.uiState.collectAsState().value
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }
    val formatted = remember { SimpleDateFormat("EEE, MMM d - h:mm a", Locale.getDefault()) }
    val (errorText, setErrorText) = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(reminderId) {
        if (reminderId != null) {
            viewModel.loadReminder(reminderId)
        }
    }

    val scheduledAt = state.scheduledAtMillis
    val scheduledLabel = scheduledAt?.let { formatted.format(Date(it)) } ?: "Pick date and time"

    LaunchedEffect(scheduledAt) {
        if (scheduledAt != null) {
            calendar.timeInMillis = scheduledAt
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (reminderId != null) "Edit Reminder" else "Add Reminder") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.title,
                onValueChange = viewModel::updateTitle,
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.details,
                onValueChange = viewModel::updateDetails,
                label = { Text("Details") },
                modifier = Modifier.fillMaxWidth()
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Event, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(scheduledLabel, style = MaterialTheme.typography.bodyMedium)
                    IconButton(onClick = {
                        DatePickerDialog(
                            context,
                            { _, year, month, day ->
                                calendar.set(Calendar.YEAR, year)
                                calendar.set(Calendar.MONTH, month)
                                calendar.set(Calendar.DAY_OF_MONTH, day)
                                val timePicker = TimePickerDialog(
                                    context,
                                    { _, hour, minute ->
                                        calendar.set(Calendar.HOUR_OF_DAY, hour)
                                        calendar.set(Calendar.MINUTE, minute)
                                        calendar.set(Calendar.SECOND, 0)
                                        calendar.set(Calendar.MILLISECOND, 0)
                                        viewModel.updateScheduledAt(calendar.timeInMillis)
                                        setErrorText(null)
                                    },
                                    calendar.get(Calendar.HOUR_OF_DAY),
                                    calendar.get(Calendar.MINUTE),
                                    false
                                )
                                timePicker.show()
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        ).show()
                    }) {
                        Icon(Icons.Filled.Schedule, contentDescription = "Pick time")
                    }
                }
            }

            if (errorText != null) {
                Text(errorText, color = MaterialTheme.colorScheme.error)
            }

            Button(onClick = {
                val now = System.currentTimeMillis()
                if (scheduledAt == null) {
                    setErrorText("Pick a date and time.")
                    return@Button
                }
                if (scheduledAt <= now) {
                    setErrorText("Time must be in the future.")
                    return@Button
                }
                viewModel.saveReminder(reminderId, onSaved)
            }) {
                Text("Save Reminder")
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !viewModel.canScheduleExactAlarms()) {
                Button(onClick = {
                    context.startActivity(android.content.Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM))
                }) {
                    Text("Enable Exact Alarms")
                }
            }
        }
    }
}
