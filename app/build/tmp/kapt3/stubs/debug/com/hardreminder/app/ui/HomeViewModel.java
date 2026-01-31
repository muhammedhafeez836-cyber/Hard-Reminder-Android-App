package com.hardreminder.app.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\u001c\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\tH\u0002J\u0018\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020 J\u0006\u0010\"\u001a\u00020\u0015J\u000e\u0010#\u001a\u00020 2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&J\u0018\u0010\'\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010(\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/hardreminder/app/ui/HomeViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "alarmScheduler", "Lcom/hardreminder/app/alarm/AlarmScheduler;", "calendarState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/hardreminder/app/ui/CalendarUiState;", "getCalendarState", "()Lkotlinx/coroutines/flow/StateFlow;", "reminders", "", "Lcom/hardreminder/app/data/ReminderEntity;", "getReminders", "repo", "Lcom/hardreminder/app/data/ReminderRepository;", "uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "canScheduleExactAlarms", "", "computeRange", "Lkotlin/Pair;", "", "state", "endOfDayMillis", "date", "Ljava/time/LocalDate;", "zone", "Ljava/time/ZoneId;", "goNext", "", "goPrevious", "isIgnoringBatteryOptimizations", "selectDate", "setViewMode", "mode", "Lcom/hardreminder/app/ui/CalendarViewMode;", "startOfDayMillis", "startOfWeek", "app_debug"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
public final class HomeViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hardreminder.app.data.ReminderRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hardreminder.app.alarm.AlarmScheduler alarmScheduler = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hardreminder.app.ui.CalendarUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hardreminder.app.ui.CalendarUiState> calendarState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.hardreminder.app.data.ReminderEntity>> reminders = null;
    
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hardreminder.app.ui.CalendarUiState> getCalendarState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.hardreminder.app.data.ReminderEntity>> getReminders() {
        return null;
    }
    
    public final void setViewMode(@org.jetbrains.annotations.NotNull()
    com.hardreminder.app.ui.CalendarViewMode mode) {
    }
    
    public final void selectDate(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date) {
    }
    
    public final void goPrevious() {
    }
    
    public final void goNext() {
    }
    
    private final kotlin.Pair<java.lang.Long, java.lang.Long> computeRange(com.hardreminder.app.ui.CalendarUiState state) {
        return null;
    }
    
    private final java.time.LocalDate startOfWeek(java.time.LocalDate date) {
        return null;
    }
    
    private final long startOfDayMillis(java.time.LocalDate date, java.time.ZoneId zone) {
        return 0L;
    }
    
    private final long endOfDayMillis(java.time.LocalDate date, java.time.ZoneId zone) {
        return 0L;
    }
    
    public final boolean canScheduleExactAlarms() {
        return false;
    }
    
    public final boolean isIgnoringBatteryOptimizations() {
        return false;
    }
}