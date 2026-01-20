package com.hardreminder.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SnoozeSettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit
) {
    val state = viewModel.uiState.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Snooze Options") },
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Set three snooze durations (minutes).", style = MaterialTheme.typography.bodyMedium)
            OutlinedTextField(
                value = state.option1,
                onValueChange = viewModel::updateOption1,
                label = { Text("Option 1 (minutes)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.option2,
                onValueChange = viewModel::updateOption2,
                label = { Text("Option 2 (minutes)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.option3,
                onValueChange = viewModel::updateOption3,
                label = { Text("Option 3 (minutes)") },
                modifier = Modifier.fillMaxWidth()
            )
            if (state.error != null) {
                Text(state.error, color = MaterialTheme.colorScheme.error)
            }
            Button(onClick = { viewModel.save(onBack) }) {
                Text("Save")
            }
        }
    }
}
