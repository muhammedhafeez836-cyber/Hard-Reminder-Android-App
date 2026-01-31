package com.hardreminder.app.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ.\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u00162\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00160\u001eJ\"\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00160\u001e2\u0006\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\bJ\u0016\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u001e2\u0006\u0010\u0007\u001a\u00020\bJ\u0012\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00160\u001eJ\u001a\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00160\u001e2\u0006\u0010\u000f\u001a\u00020\bJ&\u0010%\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010&\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\'J\u0016\u0010(\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/hardreminder/app/data/ReminderRepository;", "", "dao", "Lcom/hardreminder/app/data/ReminderDao;", "(Lcom/hardreminder/app/data/ReminderDao;)V", "clearRinging", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createReminder", "title", "", "details", "scheduledAtMillis", "nowMillis", "(Ljava/lang/String;Ljava/lang/String;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReminder", "reminder", "Lcom/hardreminder/app/data/ReminderEntity;", "(Lcom/hardreminder/app/data/ReminderEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllReminders", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReminder", "getRingingCount", "", "getRingingReminders", "markDueAsRinging", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "observeBetween", "startMillis", "endMillis", "observeReminder", "observeRinging", "observeUpcoming", "snoozeReminder", "nextAtMillis", "(JJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateReminder", "app_debug"})
public final class ReminderRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.hardreminder.app.data.ReminderDao dao = null;
    
    public ReminderRepository(@org.jetbrains.annotations.NotNull()
    com.hardreminder.app.data.ReminderDao dao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.hardreminder.app.data.ReminderEntity>> observeUpcoming(long nowMillis) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.hardreminder.app.data.ReminderEntity>> observeBetween(long startMillis, long endMillis) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.hardreminder.app.data.ReminderEntity>> observeAll() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.hardreminder.app.data.ReminderEntity>> observeRinging() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRingingReminders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hardreminder.app.data.ReminderEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.hardreminder.app.data.ReminderEntity> observeReminder(long id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getReminder(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hardreminder.app.data.ReminderEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createReminder(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String details, long scheduledAtMillis, long nowMillis, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateReminder(@org.jetbrains.annotations.NotNull()
    com.hardreminder.app.data.ReminderEntity reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteReminder(@org.jetbrains.annotations.NotNull()
    com.hardreminder.app.data.ReminderEntity reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markDueAsRinging(long nowMillis, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hardreminder.app.data.ReminderEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object snoozeReminder(long id, long nextAtMillis, long nowMillis, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearRinging(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRingingCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllReminders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hardreminder.app.data.ReminderEntity>> $completion) {
        return null;
    }
}