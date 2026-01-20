package com.hardreminder.app.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J#\u0010\u0016\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u0018\u00a2\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0015J\u000e\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006 "}, d2 = {"Lcom/hardreminder/app/ui/AddEditViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/hardreminder/app/ui/AddEditUiState;", "alarmScheduler", "Lcom/hardreminder/app/alarm/AlarmScheduler;", "repo", "Lcom/hardreminder/app/data/ReminderRepository;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "canScheduleExactAlarms", "", "loadReminder", "", "reminderId", "", "saveReminder", "onDone", "Lkotlin/Function0;", "(Ljava/lang/Long;Lkotlin/jvm/functions/Function0;)V", "updateDetails", "value", "", "updateScheduledAt", "millis", "updateTitle", "app_debug"})
public final class AddEditViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hardreminder.app.data.ReminderRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hardreminder.app.alarm.AlarmScheduler alarmScheduler = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hardreminder.app.ui.AddEditUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hardreminder.app.ui.AddEditUiState> uiState = null;
    
    public AddEditViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hardreminder.app.ui.AddEditUiState> getUiState() {
        return null;
    }
    
    public final void loadReminder(long reminderId) {
    }
    
    public final void updateTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateDetails(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateScheduledAt(long millis) {
    }
    
    public final void saveReminder(@org.jetbrains.annotations.Nullable()
    java.lang.Long reminderId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
    }
    
    public final boolean canScheduleExactAlarms() {
        return false;
    }
}