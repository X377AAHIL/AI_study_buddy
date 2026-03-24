package com.student.aistudybuddy.viewmodel;

import android.content.Context;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.lifecycle.ViewModel;
import com.student.aistudybuddy.data.local.AppDatabase;
import com.student.aistudybuddy.data.model.ChatMessage;
import com.student.aistudybuddy.data.model.ChatSession;
import com.student.aistudybuddy.data.repository.GeminiRepository;
import com.student.aistudybuddy.utils.TTSManager;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\"J\b\u0010$\u001a\u00020\"H\u0014J\u000e\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\tJ\u000e\u0010\'\u001a\u00020\"2\u0006\u0010(\u001a\u00020\tR\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u000fR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000fR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/student/aistudybuddy/viewmodel/ChatViewModel;", "Landroidx/lifecycle/ViewModel;", "db", "Lcom/student/aistudybuddy/data/local/AppDatabase;", "appContext", "Landroid/content/Context;", "(Lcom/student/aistudybuddy/data/local/AppDatabase;Landroid/content/Context;)V", "_speakingMessage", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "chatHistory", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/student/aistudybuddy/data/model/ChatSession;", "getChatHistory", "()Lkotlinx/coroutines/flow/StateFlow;", "errorMessage", "getErrorMessage", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "isLoading", "", "isSpeaking", "messages", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Lcom/student/aistudybuddy/data/model/ChatMessage;", "getMessages", "()Landroidx/compose/runtime/snapshots/SnapshotStateList;", "repository", "Lcom/student/aistudybuddy/data/repository/GeminiRepository;", "speakingMessage", "getSpeakingMessage", "ttsManager", "Lcom/student/aistudybuddy/utils/TTSManager;", "clearChat", "", "clearError", "onCleared", "sendMessage", "userText", "toggleSpeak", "text", "app_debug"})
public final class ChatViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.student.aistudybuddy.data.local.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.snapshots.SnapshotStateList<com.student.aistudybuddy.data.model.ChatMessage> messages = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _speakingMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> speakingMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.student.aistudybuddy.data.model.ChatSession>> chatHistory = null;
    @org.jetbrains.annotations.NotNull()
    private final com.student.aistudybuddy.data.repository.GeminiRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.student.aistudybuddy.utils.TTSManager ttsManager = null;
    
    public ChatViewModel(@org.jetbrains.annotations.NotNull()
    com.student.aistudybuddy.data.local.AppDatabase db, @org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.snapshots.SnapshotStateList<com.student.aistudybuddy.data.model.ChatMessage> getMessages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> getErrorMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSpeaking() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSpeakingMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.student.aistudybuddy.data.model.ChatSession>> getChatHistory() {
        return null;
    }
    
    public final void sendMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String userText) {
    }
    
    public final void clearError() {
    }
    
    public final void clearChat() {
    }
    
    public final void toggleSpeak(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}