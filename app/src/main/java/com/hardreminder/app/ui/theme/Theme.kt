package com.hardreminder.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Sky,
    onPrimary = WarmWhite,
    secondary = Mint,
    onSecondary = WarmBlack,
    background = WarmWhite,
    onBackground = WarmBlack,
    surface = WarmWhite,
    onSurface = WarmBlack,
    error = AlarmRed,
    onError = WarmWhite
)

private val DarkColors = darkColorScheme(
    primary = Sky,
    onPrimary = WarmBlack,
    secondary = Mint,
    onSecondary = WarmBlack,
    background = WarmBlack,
    onBackground = WarmWhite,
    surface = Slate,
    onSurface = WarmWhite,
    error = AlarmRed,
    onError = WarmWhite
)

@Composable
fun HardReminderTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}
