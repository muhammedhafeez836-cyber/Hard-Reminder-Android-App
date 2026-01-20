package com.hardreminder.app.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object Routes {
    const val HOME = "home"
    const val ADD = "add"
    const val EDIT = "edit"
    const val DETAIL = "detail"
    const val BATTERY = "battery"
    const val SNOOZE_SETTINGS = "snooze_settings"
    const val TONE_SETTINGS = "tone_settings"
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            val viewModel: HomeViewModel = viewModel()
            HomeScreen(
                viewModel = viewModel,
                onAdd = { navController.navigate(Routes.ADD) },
                onDetail = { id -> navController.navigate("${Routes.DETAIL}/$id") },
                onBattery = { navController.navigate(Routes.BATTERY) },
                onSnoozeSettings = { navController.navigate(Routes.SNOOZE_SETTINGS) },
                onToneSettings = { navController.navigate(Routes.TONE_SETTINGS) }
            )
        }
        composable(Routes.ADD) {
            val viewModel: AddEditViewModel = viewModel()
            AddEditReminderScreen(
                viewModel = viewModel,
                onSaved = { navController.popBackStack() },
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Routes.EDIT}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            val reminderId = backStackEntry.arguments?.getLong("id") ?: 0L
            val viewModel: AddEditViewModel = viewModel()
            AddEditReminderScreen(
                viewModel = viewModel,
                reminderId = reminderId,
                onSaved = { navController.popBackStack() },
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Routes.DETAIL}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            val reminderId = backStackEntry.arguments?.getLong("id") ?: 0L
            val viewModel: ReminderDetailViewModel = viewModel()
            ReminderDetailScreen(
                viewModel = viewModel,
                reminderId = reminderId,
                onEdit = { navController.navigate("${Routes.EDIT}/$reminderId") },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.BATTERY) {
            BatteryOptimizationScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.SNOOZE_SETTINGS) {
            val viewModel: SettingsViewModel = viewModel()
            SnoozeSettingsScreen(viewModel = viewModel, onBack = { navController.popBackStack() })
        }
        composable(Routes.TONE_SETTINGS) {
            val viewModel: SettingsViewModel = viewModel()
            ToneSettingsScreen(viewModel = viewModel, onBack = { navController.popBackStack() })
        }
    }
}
