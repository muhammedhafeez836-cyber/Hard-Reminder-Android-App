package com.hardreminder.app.alarm;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t2\u0006\u0010\u000b\u001a\u00020\fJ&\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/hardreminder/app/alarm/SnoozeSettingsStore;", "", "()V", "KEY_OPTION_1", "", "KEY_OPTION_2", "KEY_OPTION_3", "PREFS_NAME", "getOptions", "", "Lcom/hardreminder/app/alarm/SnoozeOption;", "context", "Landroid/content/Context;", "getOptionsMinutes", "", "saveOptions", "", "option1", "option2", "option3", "app_debug"})
public final class SnoozeSettingsStore {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "snooze_settings";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_OPTION_1 = "option_1";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_OPTION_2 = "option_2";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_OPTION_3 = "option_3";
    @org.jetbrains.annotations.NotNull()
    public static final com.hardreminder.app.alarm.SnoozeSettingsStore INSTANCE = null;
    
    private SnoozeSettingsStore() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hardreminder.app.alarm.SnoozeOption> getOptions(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Integer> getOptionsMinutes(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    public final void saveOptions(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int option1, int option2, int option3) {
    }
}