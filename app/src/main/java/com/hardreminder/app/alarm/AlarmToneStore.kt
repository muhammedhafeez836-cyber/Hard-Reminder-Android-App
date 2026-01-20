package com.hardreminder.app.alarm

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri

object AlarmToneStore {
    private const val PREFS_NAME = "alarm_tone_settings"
    private const val KEY_TONE_URI = "tone_uri"

    fun getToneUri(context: Context): Uri? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val value = prefs.getString(KEY_TONE_URI, null) ?: return null
        return Uri.parse(value)
    }

    fun saveToneUri(context: Context, uri: Uri?) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_TONE_URI, uri?.toString()).apply()
    }

    fun getFallbackUri(): Uri {
        return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    }
}
