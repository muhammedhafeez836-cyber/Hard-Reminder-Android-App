package com.hardreminder.app.alarm;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u000e\u0010\u0013\u001a\u00020\u0014H\u0082@\u00a2\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\b\u0010\u001b\u001a\u00020\u0014H\u0016J\"\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0016J\u000e\u0010 \u001a\u00020\u0014H\u0082@\u00a2\u0006\u0002\u0010\u0015J\b\u0010!\u001a\u00020\u0014H\u0002J\b\u0010\"\u001a\u00020\u0014H\u0002J\u000e\u0010#\u001a\u00020\u0014H\u0082@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0018\u00010\u000eR\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/hardreminder/app/alarm/AlarmRingerService;", "Landroid/app/Service;", "()V", "isStarting", "", "mediaPlayer", "Landroid/media/MediaPlayer;", "playbackLock", "", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "vibrator", "Landroid/os/Vibrator;", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "createPlayer", "uri", "Landroid/net/Uri;", "handleAlarmTriggered", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "refreshNotification", "startAlarmPlayback", "stopAlarmPlayback", "stopIfNoRinging", "Companion", "app_debug"})
public final class AlarmRingerService extends android.app.Service {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    @org.jetbrains.annotations.Nullable()
    private android.media.MediaPlayer mediaPlayer;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.Object playbackLock = null;
    @kotlin.jvm.Volatile()
    private volatile boolean isStarting = false;
    @org.jetbrains.annotations.Nullable()
    private android.os.PowerManager.WakeLock wakeLock;
    @org.jetbrains.annotations.Nullable()
    private android.os.Vibrator vibrator;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_ALARM_TRIGGERED = "com.hardreminder.app.ACTION_ALARM_TRIGGERED";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_STOP_IF_NO_RINGING = "com.hardreminder.app.ACTION_STOP_IF_NO_RINGING";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_REFRESH_NOTIFICATION = "com.hardreminder.app.ACTION_REFRESH_NOTIFICATION";
    public static final int NOTIFICATION_ID = 1001;
    @org.jetbrains.annotations.NotNull()
    public static final com.hardreminder.app.alarm.AlarmRingerService.Companion Companion = null;
    
    public AlarmRingerService() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final java.lang.Object handleAlarmTriggered(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object refreshNotification(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object stopIfNoRinging(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void startAlarmPlayback() {
    }
    
    private final android.media.MediaPlayer createPlayer(android.net.Uri uri) {
        return null;
    }
    
    private final void stopAlarmPlayback() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/hardreminder/app/alarm/AlarmRingerService$Companion;", "", "()V", "ACTION_ALARM_TRIGGERED", "", "ACTION_REFRESH_NOTIFICATION", "ACTION_STOP_IF_NO_RINGING", "NOTIFICATION_ID", "", "requestRefresh", "", "context", "Landroid/content/Context;", "requestStopIfNoRinging", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void requestStopIfNoRinging(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
        
        public final void requestRefresh(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
    }
}