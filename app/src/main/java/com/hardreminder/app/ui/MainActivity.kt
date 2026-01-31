package com.hardreminder.app.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.hardreminder.app.ui.theme.HardReminderTheme

class MainActivity : ComponentActivity() {
    private var pendingReminderId by mutableStateOf<Long?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pendingReminderId = intent.getLongExtra(EXTRA_REMINDER_ID, -1L)
            .takeIf { it > 0L }
        setContent {
            val navController = rememberNavController()
            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { }

            LaunchedEffect(Unit) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val granted = ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                    if (!granted) {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }
            }

            LaunchedEffect(pendingReminderId) {
                val id = pendingReminderId ?: return@LaunchedEffect
                navController.navigate("${Routes.DETAIL}/$id") {
                    popUpTo(Routes.HOME) { inclusive = false }
                    launchSingleTop = true
                }
                pendingReminderId = null
            }

            HardReminderTheme(darkTheme = isSystemInDarkTheme()) {
                AppNavHost(navController)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        pendingReminderId = intent.getLongExtra(EXTRA_REMINDER_ID, -1L)
            .takeIf { it > 0L }
    }

    companion object {
        const val EXTRA_REMINDER_ID = "extra_reminder_id"
    }
}
