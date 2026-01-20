package com.hardreminder.app;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/hardreminder/app/AppContainer;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "alarmScheduler", "Lcom/hardreminder/app/alarm/AlarmScheduler;", "getAlarmScheduler", "()Lcom/hardreminder/app/alarm/AlarmScheduler;", "database", "Lcom/hardreminder/app/data/AppDatabase;", "reminderRepository", "Lcom/hardreminder/app/data/ReminderRepository;", "getReminderRepository", "()Lcom/hardreminder/app/data/ReminderRepository;", "app_debug"})
public final class AppContainer {
    @org.jetbrains.annotations.NotNull()
    private final com.hardreminder.app.data.AppDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hardreminder.app.data.ReminderRepository reminderRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hardreminder.app.alarm.AlarmScheduler alarmScheduler = null;
    
    public AppContainer(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hardreminder.app.data.ReminderRepository getReminderRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hardreminder.app.alarm.AlarmScheduler getAlarmScheduler() {
        return null;
    }
}