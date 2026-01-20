package com.hardreminder.app.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0015"}, d2 = {"Lcom/hardreminder/app/ui/AlarmViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "alarmScheduler", "Lcom/hardreminder/app/alarm/AlarmScheduler;", "repo", "Lcom/hardreminder/app/data/ReminderRepository;", "ringing", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/hardreminder/app/data/ReminderEntity;", "getRinging", "()Lkotlinx/coroutines/flow/StateFlow;", "snooze", "", "reminderId", "", "minutes", "", "app_debug"})
public final class AlarmViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hardreminder.app.data.ReminderRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hardreminder.app.alarm.AlarmScheduler alarmScheduler = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.hardreminder.app.data.ReminderEntity>> ringing = null;
    
    public AlarmViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.hardreminder.app.data.ReminderEntity>> getRinging() {
        return null;
    }
    
    public final void snooze(long reminderId, int minutes) {
    }
}