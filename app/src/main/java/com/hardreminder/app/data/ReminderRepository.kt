package com.hardreminder.app.data

import kotlinx.coroutines.flow.Flow

class ReminderRepository(private val dao: ReminderDao) {
    fun observeUpcoming(nowMillis: Long): Flow<List<ReminderEntity>> = dao.observeUpcoming(nowMillis)

    fun observeRinging(): Flow<List<ReminderEntity>> = dao.observeRingingReminders()

    suspend fun getRingingReminders(): List<ReminderEntity> = dao.getRingingReminders()

    fun observeReminder(id: Long): Flow<ReminderEntity?> = dao.observeReminder(id)

    suspend fun getReminder(id: Long): ReminderEntity? = dao.getReminder(id)

    suspend fun createReminder(
        title: String,
        details: String,
        scheduledAtMillis: Long,
        nowMillis: Long
    ): Long {
        val reminder = ReminderEntity(
            title = title,
            details = details,
            scheduledAtMillis = scheduledAtMillis,
            createdAtMillis = nowMillis,
            updatedAtMillis = nowMillis,
            snoozeCount = 0,
            isRinging = false
        )
        return dao.insert(reminder)
    }

    suspend fun updateReminder(reminder: ReminderEntity) {
        dao.update(reminder)
    }

    suspend fun deleteReminder(reminder: ReminderEntity) {
        dao.delete(reminder)
    }

    suspend fun markDueAsRinging(nowMillis: Long): List<ReminderEntity> {
        val due = dao.getDueReminders(nowMillis)
        if (due.isNotEmpty()) {
            dao.markRinging(due.map { it.id })
        }
        return due
    }

    suspend fun snoozeReminder(id: Long, nextAtMillis: Long, nowMillis: Long) {
        dao.snoozeReminder(id, nextAtMillis, nowMillis)
    }

    suspend fun clearRinging(id: Long) {
        dao.clearRinging(id)
    }

    suspend fun getRingingCount(): Int = dao.getRingingCount()

    suspend fun getAllReminders(): List<ReminderEntity> = dao.getAllReminders()
}
