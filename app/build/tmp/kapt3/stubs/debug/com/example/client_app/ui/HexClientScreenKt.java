package com.example.client_app.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a,\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0007\u001a\u001e\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a\u0010\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u000f"}, d2 = {"ControlButtons", "", "onStart", "Lkotlin/Function0;", "onStop", "isGenerating", "", "HexClientScreen", "viewModel", "Lcom/example/client_app/ui/HexViewModel;", "HexCodeCard", "hexCode", "Lcom/example/client_app/data/HexCode;", "onDelete", "StatusIndicator", "app_debug"})
public final class HexClientScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void HexClientScreen(@org.jetbrains.annotations.NotNull()
    com.example.client_app.ui.HexViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StatusIndicator(boolean isGenerating) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ControlButtons(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onStart, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onStop, boolean isGenerating) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void HexCodeCard(@org.jetbrains.annotations.NotNull()
    com.example.client_app.data.HexCode hexCode, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
}