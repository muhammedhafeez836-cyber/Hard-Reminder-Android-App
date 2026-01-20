package com.hardreminder.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val details: String,
    val scheduledAtMillis: Long,
    val createdAtMillis: Long,
    val updatedAtMillis: Long,
    val snoozeCount: Int,
    val isRinging: Boolean
)
