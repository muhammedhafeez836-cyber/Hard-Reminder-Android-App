package com.hardreminder.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hardreminder.app.alarm.SnoozeSettingsStore
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ReminderDetailScreen(
    viewModel: ReminderDetailViewModel,
    reminderId: Long,
    onEdit: () -> Unit,
    onBack: () -> Unit
) {
    val reminder = viewModel.reminder.collectAsState().value
    val ringingCount = viewModel.ringingCount.collectAsState().value
    val formatter = rememberDateFormatter()
    val (confirmDelete, setConfirmDelete) = remember { mutableStateOf(false) }
    val (showSnooze, setShowSnooze) = remember { mutableStateOf(false) }

    LaunchedEffect(reminderId) {
        viewModel.loadReminder(reminderId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reminder") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit")
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
            if (reminder == null) {
                Text("Reminder not found.")
                return@Column
            }
            Text(reminder.title, style = MaterialTheme.typography.headlineSmall)
            if (reminder.details.isNotBlank()) {
                Text(reminder.details, style = MaterialTheme.typography.bodyMedium)
            }
            Text(
                "Scheduled: ${formatter.format(Date(reminder.scheduledAtMillis))}",
                style = MaterialTheme.typography.bodySmall
            )
            Text("Snoozed: ${reminder.snoozeCount} times")
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { setShowSnooze(true) }, enabled = ringingCount > 0) {
                    Text("Snooze")
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(onClick = onEdit) {
                        Icon(Icons.Filled.Edit, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Edit")
                    }
                    Button(onClick = { setConfirmDelete(true) }) {
                        Icon(Icons.Filled.Delete, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Delete")
                    }
                }
            }
        }
    }

    if (showSnooze) {
        val options = SnoozeSettingsStore.getOptions(LocalContext.current)
        AlertDialog(
            onDismissRequest = { setShowSnooze(false) },
            title = { Text("Choose snooze duration") },
            text = {
                Column(
                    modifier = Modifier.heightIn(max = 240.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    options.forEach { option ->
                        TextButton(onClick = {
                            viewModel.snooze(option.minutes)
                            setShowSnooze(false)
                        }) {
                            Text(option.label)
                        }
                    }
                }
            },
            confirmButton = { },
            dismissButton = {
                TextButton(onClick = { setShowSnooze(false) }) {
                    Text("Close")
                }
            }
        )
    }

    if (confirmDelete) {
        AlertDialog(
            onDismissRequest = { setConfirmDelete(false) },
            title = { Text("Delete reminder?") },
            text = { Text("This will permanently delete the reminder.") },
            confirmButton = {
                TextButton(onClick = {
                    setConfirmDelete(false)
                    viewModel.deleteReminder(onBack)
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { setConfirmDelete(false) }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun rememberDateFormatter(): SimpleDateFormat {
    return SimpleDateFormat("EEE, MMM d - h:mm a", Locale.getDefault())
}
