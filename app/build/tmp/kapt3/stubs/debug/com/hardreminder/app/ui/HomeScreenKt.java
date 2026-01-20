package com.hardreminder.app.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a\\\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a&\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a\b\u0010\u0013\u001a\u00020\u0014H\u0003\u00a8\u0006\u0015"}, d2 = {"BatteryOptimizationScreen", "", "onBack", "Lkotlin/Function0;", "HomeScreen", "viewModel", "Lcom/hardreminder/app/ui/HomeViewModel;", "onAdd", "onDetail", "Lkotlin/Function1;", "", "onBattery", "onSnoozeSettings", "onToneSettings", "WarningCard", "text", "", "actionText", "onAction", "rememberDateTimeFormatter", "Ljava/text/SimpleDateFormat;", "app_debug"})
public final class HomeScreenKt {
    
    @androidx.compose.runtime.Composable()
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull()
    com.hardreminder.app.ui.HomeViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAdd, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onDetail, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBattery, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSnoozeSettings, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onToneSettings) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void WarningCard(java.lang.String text, java.lang.String actionText, kotlin.jvm.functions.Function0<kotlin.Unit> onAction) {
    }
    
    @androidx.compose.runtime.Composable()
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void BatteryOptimizationScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final java.text.SimpleDateFormat rememberDateTimeFormatter() {
        return null;
    }
}