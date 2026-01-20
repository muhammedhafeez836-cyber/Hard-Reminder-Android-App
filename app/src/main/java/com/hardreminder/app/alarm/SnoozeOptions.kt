package com.hardreminder.app.alarm

data class SnoozeOption(val minutes: Int) {
    val label: String = "$minutes min"
}

val defaultSnoozeMinutes = listOf(5, 20, 60)
