package com.hardreminder.app.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\f2\u0006\u0010\u000f\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u0014\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u0015\u001a\u00020\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u0014\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f0\u0019H\'J$\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f0\u00192\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\'J\u0018\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00192\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0014\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f0\u0019H\'J\u001c\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f0\u00192\u0006\u0010\u000f\u001a\u00020\u0005H\'J&\u0010 \u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006%"}, d2 = {"Lcom/hardreminder/app/data/ReminderDao;", "", "clearRinging", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "reminder", "Lcom/hardreminder/app/data/ReminderEntity;", "(Lcom/hardreminder/app/data/ReminderEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllReminders", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDueReminders", "nowMillis", "getReminder", "getRingingCount", "", "getRingingReminders", "insert", "markRinging", "ids", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "observeBetween", "startMillis", "endMillis", "observeReminder", "observeRingingReminders", "observeUpcoming", "snoozeReminder", "scheduledAtMillis", "updatedAtMillis", "(JJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface ReminderDao {
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE scheduledAtMillis >= :nowMillis AND isRinging = 0 ORDER BY scheduledAtMillis ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hardreminder.app.data.ReminderEntity>> observeUpcoming(long nowMillis);
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE scheduledAtMillis BETWEEN :startMillis AND :endMillis ORDER BY scheduledAtMillis ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hardreminder.app.data.ReminderEntity>> observeBetween(long startMillis, long endMillis);
    
    @androidx.room.Query(value = "SELECT * FROM reminders ORDER BY scheduledAtMillis ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hardreminder.app.data.ReminderEntity>> observeAll();
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE isRinging = 1 ORDER BY scheduledAtMillis ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hardreminder.app.data.ReminderEntity>> observeRingingReminders();
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE isRinging = 1 ORDER BY scheduledAtMillis ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRingingReminders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hardreminder.app.data.ReminderEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE id = :id")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hardreminder.app.data.ReminderEntity> observeReminder(long id);
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReminder(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hardreminder.app.data.ReminderEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE scheduledAtMillis <= :nowMillis AND isRinging = 0 ORDER BY scheduledAtMillis ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDueReminders(long nowMillis, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hardreminder.app.data.ReminderEntity>> $completion);
    
    @androidx.room.Query(value = "UPDATE reminders SET isRinging = 1 WHERE id IN (:ids)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markRinging(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> ids, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE reminders SET isRinging = 0 WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearRinging(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE reminders SET scheduledAtMillis = :scheduledAtMillis, snoozeCount = snoozeCount + 1, isRinging = 0, updatedAtMillis = :updatedAtMillis WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object snoozeReminder(long id, long scheduledAtMillis, long updatedAtMillis, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM reminders WHERE isRinging = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRingingCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM reminders")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllReminders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hardreminder.app.data.ReminderEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.hardreminder.app.data.ReminderEntity reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.hardreminder.app.data.ReminderEntity reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.hardreminder.app.data.ReminderEntity reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}