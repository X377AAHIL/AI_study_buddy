package com.student.aistudybuddy.viewmodel;

import androidx.lifecycle.ViewModel;
import com.student.aistudybuddy.data.local.AppDatabase;
import com.student.aistudybuddy.data.model.ChatSession;
import com.student.aistudybuddy.data.model.QuizResult;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000bR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\r\u00a8\u0006\u0015"}, d2 = {"Lcom/student/aistudybuddy/viewmodel/HistoryViewModel;", "Landroidx/lifecycle/ViewModel;", "db", "Lcom/student/aistudybuddy/data/local/AppDatabase;", "(Lcom/student/aistudybuddy/data/local/AppDatabase;)V", "_isLoading", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "chatSessions", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/student/aistudybuddy/data/model/ChatSession;", "getChatSessions", "()Lkotlinx/coroutines/flow/StateFlow;", "isLoading", "quizResults", "Lcom/student/aistudybuddy/data/model/QuizResult;", "getQuizResults", "deleteSession", "", "session", "app_debug"})
public final class HistoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.student.aistudybuddy.data.local.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.student.aistudybuddy.data.model.ChatSession>> chatSessions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.student.aistudybuddy.data.model.QuizResult>> quizResults = null;
    
    public HistoryViewModel(@org.jetbrains.annotations.NotNull()
    com.student.aistudybuddy.data.local.AppDatabase db) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.student.aistudybuddy.data.model.ChatSession>> getChatSessions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.student.aistudybuddy.data.model.QuizResult>> getQuizResults() {
        return null;
    }
    
    public final void deleteSession(@org.jetbrains.annotations.NotNull()
    com.student.aistudybuddy.data.model.ChatSession session) {
    }
}