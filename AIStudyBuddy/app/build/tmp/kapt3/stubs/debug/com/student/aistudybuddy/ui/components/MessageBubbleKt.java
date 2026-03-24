package com.student.aistudybuddy.ui.components;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.style.TextOverflow;
import com.student.aistudybuddy.data.model.ChatMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u001a\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a:\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00040\r2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\u00a8\u0006\u0012"}, d2 = {"UserBubbleColor", "Landroidx/compose/ui/graphics/Color;", "J", "LoadingMessageBubble", "", "modifier", "Landroidx/compose/ui/Modifier;", "MessageBubble", "message", "Lcom/student/aistudybuddy/data/model/ChatMessage;", "isSpeaking", "", "onSpeak", "Lkotlin/Function1;", "", "formatTime", "timestamp", "", "app_debug"})
public final class MessageBubbleKt {
    private static final long UserBubbleColor = 0L;
    
    @androidx.compose.runtime.Composable()
    public static final void MessageBubble(@org.jetbrains.annotations.NotNull()
    com.student.aistudybuddy.data.model.ChatMessage message, boolean isSpeaking, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSpeak, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void LoadingMessageBubble(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    private static final java.lang.String formatTime(long timestamp) {
        return null;
    }
}