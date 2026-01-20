package com.hardreminder.app.alarm

import android.content.Context

object SnoozeSettingsStore {
    private const val PREFS_NAME = "snooze_settings"
    private const val KEY_OPTION_1 = "option_1"
    private const val KEY_OPTION_2 = "option_2"
    private const val KEY_OPTION_3 = "option_3"

    fun getOptions(context: Context): List<SnoozeOption> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val minutes = listOf(
            prefs.getInt(KEY_OPTION_1, defaultSnoozeMinutes[0]),
            prefs.getInt(KEY_OPTION_2, defaultSnoozeMinutes[1]),
            prefs.getInt(KEY_OPTION_3, defaultSnoozeMinutes[2])
        ).map { it.coerceAtLeast(1) }
        return minutes.map { SnoozeOption(it) }
    }

    fun getOptionsMinutes(context: Context): List<Int> {
        return getOptions(context).map { it.minutes }
    }

    fun saveOptions(context: Context, option1: Int, option2: Int, option3: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putInt(KEY_OPTION_1, option1.coerceAtLeast(1))
            .putInt(KEY_OPTION_2, option2.coerceAtLeast(1))
            .putInt(KEY_OPTION_3, option3.coerceAtLeast(1))
            .apply()
    }
}
