package com.hardreminder.app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminders WHERE scheduledAtMillis >= :nowMillis AND isRinging = 0 ORDER BY scheduledAtMillis ASC")
    fun observeUpcoming(nowMillis: Long): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE scheduledAtMillis BETWEEN :startMillis AND :endMillis ORDER BY scheduledAtMillis ASC")
    fun observeBetween(startMillis: Long, endMillis: Long): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders ORDER BY scheduledAtMillis ASC")
    fun observeAll(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE isRinging = 1 ORDER BY scheduledAtMillis ASC")
    fun observeRingingReminders(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE isRinging = 1 ORDER BY scheduledAtMillis ASC")
    suspend fun getRingingReminders(): List<ReminderEntity>

    @Query("SELECT * FROM reminders WHERE id = :id")
    fun observeReminder(id: Long): Flow<ReminderEntity?>

    @Query("SELECT * FROM reminders WHERE id = :id")
    suspend fun getReminder(id: Long): ReminderEntity?

    @Query("SELECT * FROM reminders WHERE scheduledAtMillis <= :nowMillis AND isRinging = 0 ORDER BY scheduledAtMillis ASC")
    suspend fun getDueReminders(nowMillis: Long): List<ReminderEntity>

    @Query("UPDATE reminders SET isRinging = 1 WHERE id IN (:ids)")
    suspend fun markRinging(ids: List<Long>)

    @Query("UPDATE reminders SET isRinging = 0 WHERE id = :id")
    suspend fun clearRinging(id: Long)

    @Query("UPDATE reminders SET scheduledAtMillis = :scheduledAtMillis, snoozeCount = snoozeCount + 1, isRinging = 0, updatedAtMillis = :updatedAtMillis WHERE id = :id")
    suspend fun snoozeReminder(id: Long, scheduledAtMillis: Long, updatedAtMillis: Long)

    @Query("SELECT COUNT(*) FROM reminders WHERE isRinging = 1")
    suspend fun getRingingCount(): Int

    @Query("SELECT * FROM reminders")
    suspend fun getAllReminders(): List<ReminderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: ReminderEntity): Long

    @Update
    suspend fun update(reminder: ReminderEntity)

    @Delete
    suspend fun delete(reminder: ReminderEntity)
}
