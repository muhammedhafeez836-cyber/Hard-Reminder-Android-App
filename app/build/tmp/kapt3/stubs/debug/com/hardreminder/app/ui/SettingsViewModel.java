package com.hardreminder.app.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\u0007H\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\u0014\u0010\u0013\u001a\u00020\u000e2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015J\u0010\u0010\u0016\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0010J\u000e\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0010J\u000e\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0010R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lcom/hardreminder/app/ui/SettingsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/hardreminder/app/ui/SnoozeSettingsUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadState", "resetToneToDefault", "", "resolveToneLabel", "", "uri", "Landroid/net/Uri;", "save", "onDone", "Lkotlin/Function0;", "setToneUri", "updateOption1", "value", "updateOption2", "updateOption3", "app_debug"})
public final class SettingsViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hardreminder.app.ui.SnoozeSettingsUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hardreminder.app.ui.SnoozeSettingsUiState> uiState = null;
    
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hardreminder.app.ui.SnoozeSettingsUiState> getUiState() {
        return null;
    }
    
    private final com.hardreminder.app.ui.SnoozeSettingsUiState loadState() {
        return null;
    }
    
    public final void updateOption1(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateOption2(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateOption3(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void save(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
    }
    
    public final void setToneUri(@org.jetbrains.annotations.Nullable()
    android.net.Uri uri) {
    }
    
    public final void resetToneToDefault() {
    }
    
    private final java.lang.String resolveToneLabel(android.net.Uri uri) {
        return null;
    }
}