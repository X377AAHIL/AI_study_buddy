package com.student.aistudybuddy.ui.screens;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.style.TextAlign;
import androidx.lifecycle.ViewModelProvider;
import com.student.aistudybuddy.viewmodel.QuizUiState;
import com.student.aistudybuddy.viewmodel.QuizViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u001a.\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\nH\u0003\u001a\u0012\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0003\u001a\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a(\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0013H\u0003\u001a&\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00040\nH\u0003\u001a\u0010\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0018H\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"SuggestedTopics", "", "", "InProgressState", "", "modifier", "Landroidx/compose/ui/Modifier;", "uiState", "Lcom/student/aistudybuddy/viewmodel/QuizUiState$InProgress;", "onAnswerSelected", "Lkotlin/Function1;", "", "LoadingState", "QuizScreen", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "ResultsState", "Lcom/student/aistudybuddy/viewmodel/QuizUiState$Results;", "onRetry", "Lkotlin/Function0;", "TopicPickerState", "onGenerateQuiz", "motivationalMessage", "progress", "", "app_debug"})
public final class QuizScreenKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> SuggestedTopics = null;
    
    @androidx.compose.runtime.Composable()
    public static final void QuizScreen(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory viewModelFactory) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TopicPickerState(androidx.compose.ui.Modifier modifier, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onGenerateQuiz) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void InProgressState(androidx.compose.ui.Modifier modifier, com.student.aistudybuddy.viewmodel.QuizUiState.InProgress uiState, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onAnswerSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ResultsState(androidx.compose.ui.Modifier modifier, com.student.aistudybuddy.viewmodel.QuizUiState.Results uiState, kotlin.jvm.functions.Function0<kotlin.Unit> onRetry) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LoadingState(androidx.compose.ui.Modifier modifier) {
    }
    
    private static final java.lang.String motivationalMessage(float progress) {
        return null;
    }
}