package com.student.aistudybuddy.ui.screens;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.lifecycle.ViewModelProvider;
import com.student.aistudybuddy.data.model.ChatMessage;
import com.student.aistudybuddy.viewmodel.ChatViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003\u001a\u0010\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001ap\u0010\f\u001a\u00020\u00012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014H\u0003\u001a:\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\b2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00142\u0006\u0010\u0019\u001a\u00020\u0005H\u0003\u00a8\u0006\u001a"}, d2 = {"AnimatedMessageBubble", "", "message", "Lcom/student/aistudybuddy/data/model/ChatMessage;", "isSpeaking", "", "onSpeak", "Lkotlin/Function1;", "", "ChatScreen", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "ChatScreenContent", "messages", "", "isLoading", "errorMessage", "speakingMessage", "onSend", "onErrorConsumed", "Lkotlin/Function0;", "InputBar", "value", "onValueChange", "onSendClick", "enabled", "app_debug"})
public final class ChatScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void ChatScreen(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory viewModelFactory) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ChatScreenContent(java.util.List<com.student.aistudybuddy.data.model.ChatMessage> messages, boolean isLoading, java.lang.String errorMessage, boolean isSpeaking, java.lang.String speakingMessage, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSend, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSpeak, kotlin.jvm.functions.Function0<kotlin.Unit> onErrorConsumed) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AnimatedMessageBubble(com.student.aistudybuddy.data.model.ChatMessage message, boolean isSpeaking, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSpeak) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void InputBar(java.lang.String value, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onValueChange, kotlin.jvm.functions.Function0<kotlin.Unit> onSendClick, boolean enabled) {
    }
}