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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    val formatter = rememberDateFormatter()

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
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = onEdit) {
                    Icon(Icons.Filled.Edit, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Edit")
                }
                Button(onClick = { viewModel.deleteReminder(onBack) }) {
                    Icon(Icons.Filled.Delete, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Delete")
                }
            }
        }
    }
}

@Composable
private fun rememberDateFormatter(): SimpleDateFormat {
    return SimpleDateFormat("EEE, MMM d - h:mm a", Locale.getDefault())
}
