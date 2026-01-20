package com.hardreminder.app.alarm;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007H\u0002J\u001c\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007J \u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/hardreminder/app/alarm/AlarmNotificationHelper;", "", "()V", "CHANNEL_ID", "", "buildContentText", "titles", "", "buildNotification", "Landroid/app/Notification;", "context", "Landroid/content/Context;", "createSnoozeAction", "Landroid/app/PendingIntent;", "minutes", "", "requestCode", "ensureChannel", "", "app_debug"})
public final class AlarmNotificationHelper {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ID = "hard_reminder_alarm";
    @org.jetbrains.annotations.NotNull()
    public static final com.hardreminder.app.alarm.AlarmNotificationHelper INSTANCE = null;
    
    private AlarmNotificationHelper() {
        super();
    }
    
    public final void ensureChannel(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification buildNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> titles) {
        return null;
    }
    
    private final android.app.PendingIntent createSnoozeAction(android.content.Context context, int minutes, int requestCode) {
        return null;
    }
    
    private final java.lang.String buildContentText(java.util.List<java.lang.String> titles) {
        return null;
    }
}