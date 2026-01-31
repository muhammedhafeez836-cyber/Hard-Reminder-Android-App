package com.hardreminder.app.ui

import android.os.Bundle
import android.view.WindowManager
import android.os.Build
import android.view.KeyEvent
import android.media.AudioManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hardreminder.app.alarm.SnoozeOption
import com.hardreminder.app.alarm.SnoozeSettingsStore
import com.hardreminder.app.alarm.AlarmRingerService
import com.hardreminder.app.ui.theme.HardReminderTheme

class AlarmActivity : ComponentActivity() {
    private val viewModel: AlarmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        volumeControlStream = AudioManager.STREAM_ALARM
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        }
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )

        setContent {
            HardReminderTheme(darkTheme = true) {
                AlarmScreen(
                    viewModel = viewModel,
                    openPicker = intent.getBooleanExtra(EXTRA_OPEN_SNOOZE_PICKER, false)
                )
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN ||
            keyCode == KeyEvent.KEYCODE_VOLUME_UP ||
            keyCode == KeyEvent.KEYCODE_VOLUME_MUTE
        ) {
            AlarmRingerService.requestSilenceAudio(this)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_VOLUME_DOWN ||
            event.keyCode == KeyEvent.KEYCODE_VOLUME_UP ||
            event.keyCode == KeyEvent.KEYCODE_VOLUME_MUTE
        ) {
            AlarmRingerService.requestSilenceAudio(this)
            return true
        }
        return super.dispatchKeyEvent(event)
    }

    companion object {
        const val EXTRA_OPEN_SNOOZE_PICKER = "extra_open_snooze_picker"
    }
}

@Composable
private fun AlarmScreen(viewModel: AlarmViewModel, openPicker: Boolean) {
    val reminders = viewModel.ringing.collectAsState().value
    val (selectedId, setSelectedId) = remember { mutableStateOf<Long?>(null) }
    val activity = LocalContext.current as? ComponentActivity
    val context = LocalContext.current
    val snoozeOptions = remember(context) { SnoozeSettingsStore.getOptions(context) }

    LaunchedEffect(openPicker, reminders) {
        if (openPicker && reminders.size == 1) {
            setSelectedId(reminders.first().id)
        }
        if (reminders.isEmpty()) {
            AlarmRingerService.requestStopIfNoRinging(context)
            activity?.finish()
        }
    }

    if (selectedId != null) {
        SnoozePickerDialog(
            onDismiss = { setSelectedId(null) },
            onPick = { minutes ->
                viewModel.snooze(selectedId, minutes)
                setSelectedId(null)
            },
            options = snoozeOptions
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Reminders due",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "You must snooze each reminder to silence the alarm.",
            style = MaterialTheme.typography.bodyMedium
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(reminders, key = { it.id }) { reminder ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = reminder.title, style = MaterialTheme.typography.titleLarge)
                        if (reminder.details.isNotBlank()) {
                            Text(text = reminder.details, style = MaterialTheme.typography.bodyMedium)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Snoozes: ${reminder.snoozeCount}")
                            Button(onClick = { setSelectedId(reminder.id) }) {
                                Text("Snooze")
                            }
                        }
                    }
                }
            }
        }
        if (reminders.isEmpty()) {
            Text("No active reminders.")
        }
    }
}

@Composable
private fun SnoozePickerDialog(
    onDismiss: () -> Unit,
    onPick: (Int) -> Unit,
    options: List<SnoozeOption>
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Choose snooze duration") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                options.forEach { option ->
                    TextButton(onClick = { onPick(option.minutes) }) {
                        Text(option.label)
                    }
                }
            }
        },
        confirmButton = { },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Close") }
        }
    )
}
