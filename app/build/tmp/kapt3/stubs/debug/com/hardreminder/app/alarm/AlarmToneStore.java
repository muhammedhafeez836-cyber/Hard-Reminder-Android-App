package com.hardreminder.app.alarm;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/hardreminder/app/alarm/AlarmToneStore;", "", "()V", "KEY_TONE_URI", "", "PREFS_NAME", "getFallbackUri", "Landroid/net/Uri;", "getToneUri", "context", "Landroid/content/Context;", "saveToneUri", "", "uri", "app_debug"})
public final class AlarmToneStore {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "alarm_tone_settings";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_TONE_URI = "tone_uri";
    @org.jetbrains.annotations.NotNull()
    public static final com.hardreminder.app.alarm.AlarmToneStore INSTANCE = null;
    
    private AlarmToneStore() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getToneUri(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    public final void saveToneUri(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.net.Uri uri) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.net.Uri getFallbackUri() {
        return null;
    }
}