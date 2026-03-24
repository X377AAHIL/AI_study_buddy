package com.student.aistudybuddy.viewmodel;

import androidx.lifecycle.ViewModel;
import com.student.aistudybuddy.data.local.AppDatabase;
import com.student.aistudybuddy.data.model.QuizQuestion;
import com.student.aistudybuddy.data.model.QuizResult;
import com.student.aistudybuddy.data.repository.GeminiRepository;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0006J\u0006\u0010\u001b\u001a\u00020\u0019J\u000e\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u001f"}, d2 = {"Lcom/student/aistudybuddy/viewmodel/QuizViewModel;", "Landroidx/lifecycle/ViewModel;", "db", "Lcom/student/aistudybuddy/data/local/AppDatabase;", "(Lcom/student/aistudybuddy/data/local/AppDatabase;)V", "currentTopic", "", "errorEvents", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "getErrorEvents", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "quizHistory", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/student/aistudybuddy/data/model/QuizResult;", "getQuizHistory", "()Lkotlinx/coroutines/flow/StateFlow;", "repository", "Lcom/student/aistudybuddy/data/repository/GeminiRepository;", "uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/student/aistudybuddy/viewmodel/QuizUiState;", "getUiState", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "generateQuiz", "", "topic", "resetQuiz", "selectAnswer", "answerIndex", "", "app_debug"})
public final class QuizViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.student.aistudybuddy.data.local.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.student.aistudybuddy.viewmodel.QuizUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<java.lang.String> errorEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.student.aistudybuddy.data.model.QuizResult>> quizHistory = null;
    @org.jetbrains.annotations.NotNull()
    private final com.student.aistudybuddy.data.repository.GeminiRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentTopic = "";
    
    public QuizViewModel(@org.jetbrains.annotations.NotNull()
    com.student.aistudybuddy.data.local.AppDatabase db) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableStateFlow<com.student.aistudybuddy.viewmodel.QuizUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableSharedFlow<java.lang.String> getErrorEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.student.aistudybuddy.data.model.QuizResult>> getQuizHistory() {
        return null;
    }
    
    public final void generateQuiz(@org.jetbrains.annotations.NotNull()
    java.lang.String topic) {
    }
    
    public final void selectAnswer(int answerIndex) {
    }
    
    public final void resetQuiz() {
    }
}