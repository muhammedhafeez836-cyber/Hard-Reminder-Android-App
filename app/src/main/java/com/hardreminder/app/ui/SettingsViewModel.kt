package com.hardreminder.app.ui

import android.app.Application
import android.net.Uri
import android.provider.OpenableColumns
import android.media.RingtoneManager
import androidx.lifecycle.AndroidViewModel
import com.hardreminder.app.alarm.AlarmToneStore
import com.hardreminder.app.alarm.SnoozeSettingsStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class SnoozeSettingsUiState(
    val option1: String = "",
    val option2: String = "",
    val option3: String = "",
    val toneLabel: String = "System default",
    val toneUri: String? = null,
    val error: String? = null
)

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(loadState())
    val uiState: StateFlow<SnoozeSettingsUiState> = _uiState

    private fun loadState(): SnoozeSettingsUiState {
        val options = SnoozeSettingsStore.getOptionsMinutes(getApplication())
        val uri = AlarmToneStore.getToneUri(getApplication())
        val label = resolveToneLabel(uri)
        return SnoozeSettingsUiState(
            option1 = options[0].toString(),
            option2 = options[1].toString(),
            option3 = options[2].toString(),
            toneLabel = label,
            toneUri = uri?.toString()
        )
    }

    fun updateOption1(value: String) {
        _uiState.value = _uiState.value.copy(option1 = value, error = null)
    }

    fun updateOption2(value: String) {
        _uiState.value = _uiState.value.copy(option2 = value, error = null)
    }

    fun updateOption3(value: String) {
        _uiState.value = _uiState.value.copy(option3 = value, error = null)
    }

    fun save(onDone: () -> Unit) {
        val option1 = _uiState.value.option1.toIntOrNull()
        val option2 = _uiState.value.option2.toIntOrNull()
        val option3 = _uiState.value.option3.toIntOrNull()
        if (option1 == null || option2 == null || option3 == null) {
            _uiState.value = _uiState.value.copy(error = "Enter valid numbers.")
            return
        }
        if (option1 <= 0 || option2 <= 0 || option3 <= 0) {
            _uiState.value = _uiState.value.copy(error = "Minutes must be greater than 0.")
            return
        }
        SnoozeSettingsStore.saveOptions(getApplication(), option1, option2, option3)
        _uiState.value = _uiState.value.copy(error = null)
        onDone()
    }

    fun setToneUri(uri: Uri?) {
        AlarmToneStore.saveToneUri(getApplication(), uri)
        val label = resolveToneLabel(uri)
        _uiState.value = _uiState.value.copy(toneLabel = label, toneUri = uri?.toString())
    }

    fun resetToneToDefault() {
        setToneUri(null)
    }

    private fun resolveToneLabel(uri: Uri?): String {
        if (uri == null) return "System default"
        val ringtone = RingtoneManager.getRingtone(getApplication(), uri)
        if (ringtone != null) {
            return ringtone.getTitle(getApplication())
        }
        val resolver = getApplication<Application>().contentResolver
        return resolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
            ?.use { cursor ->
                val index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (index >= 0 && cursor.moveToFirst()) cursor.getString(index) else null
            } ?: uri.lastPathSegment.orEmpty()
    }
}
