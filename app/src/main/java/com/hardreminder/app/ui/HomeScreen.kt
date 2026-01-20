package com.hardreminder.app.ui

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    viewModel: HomeViewModel,
    onAdd: () -> Unit,
    onDetail: (Long) -> Unit,
    onBattery: () -> Unit,
    onSnoozeSettings: () -> Unit,
    onToneSettings: () -> Unit
) {
    val reminders = viewModel.upcoming.collectAsState().value
    val dateFormatter = rememberDateTimeFormatter()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.refreshNow()
            delay(60_000)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hard Reminder") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Filled.Add, contentDescription = "Add reminder")
            }
        }
    ) { padding ->
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

            Button(onClick = onSnoozeSettings) {
                Text("Snooze options")
            }
            Button(onClick = onToneSettings) {
                Text("Alarm tone")
            }

            Text("Upcoming", style = MaterialTheme.typography.titleLarge)

            if (reminders.isEmpty()) {
                Text("No upcoming reminders.")
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 80.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(reminders, key = { it.id }) { reminder ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onDetail(reminder.id) }
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(reminder.title, style = MaterialTheme.typography.titleMedium)
                                if (reminder.details.isNotBlank()) {
                                    Text(reminder.details, style = MaterialTheme.typography.bodyMedium)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    "${dateFormatter.format(Date(reminder.scheduledAtMillis))}",
                                    style = MaterialTheme.typography.bodySmall
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
private fun WarningCard(
    text: String,
    actionText: String,
    onAction: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
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
            Button(onClick = onAction) {
                Text(actionText)
            }
        }
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
                    androidx.compose.material3.IconButton(onClick = onBack) {
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
