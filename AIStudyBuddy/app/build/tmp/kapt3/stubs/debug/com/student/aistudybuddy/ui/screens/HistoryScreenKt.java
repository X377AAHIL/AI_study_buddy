package com.student.aistudybuddy.ui.screens;

import android.util.Log;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.material3.SwipeToDismissBoxValue;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.lifecycle.ViewModelProvider;
import com.student.aistudybuddy.data.model.ChatSession;
import com.student.aistudybuddy.data.model.QuizResult;
import com.student.aistudybuddy.viewmodel.HistoryViewModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a8\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\b\u0010\b\u001a\u00020\u0001H\u0003\u001a\b\u0010\t\u001a\u00020\u0001H\u0003\u001a\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001aT\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00112\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u0010\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0013H\u0003\u001a\u0010\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018H\u0003\u001a\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\u001a\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0018H\u0002\u00a8\u0006\u001e"}, d2 = {"ChatSessionItem", "", "session", "Lcom/student/aistudybuddy/data/model/ChatSession;", "onChatClick", "Lkotlin/Function1;", "DismissibleChatSessionItem", "onDeleteChat", "EmptyChatsState", "HistoryLoadingContent", "HistoryScreen", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "HistoryScreenContent", "isLoading", "", "chatSessions", "", "quizResults", "Lcom/student/aistudybuddy/data/model/QuizResult;", "QuizResultItem", "result", "SectionHeader", "title", "", "relativeTime", "timestamp", "", "snippet", "text", "app_debug"})
public final class HistoryScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void HistoryScreen(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory viewModelFactory) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void HistoryScreenContent(boolean isLoading, java.util.List<com.student.aistudybuddy.data.model.ChatSession> chatSessions, java.util.List<com.student.aistudybuddy.data.model.QuizResult> quizResults, kotlin.jvm.functions.Function1<? super com.student.aistudybuddy.data.model.ChatSession, kotlin.Unit> onChatClick, kotlin.jvm.functions.Function1<? super com.student.aistudybuddy.data.model.ChatSession, kotlin.Unit> onDeleteChat) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void HistoryLoadingContent() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SectionHeader(java.lang.String title) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void DismissibleChatSessionItem(com.student.aistudybuddy.data.model.ChatSession session, kotlin.jvm.functions.Function1<? super com.student.aistudybuddy.data.model.ChatSession, kotlin.Unit> onChatClick, kotlin.jvm.functions.Function1<? super com.student.aistudybuddy.data.model.ChatSession, kotlin.Unit> onDeleteChat) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ChatSessionItem(com.student.aistudybuddy.data.model.ChatSession session, kotlin.jvm.functions.Function1<? super com.student.aistudybuddy.data.model.ChatSession, kotlin.Unit> onChatClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QuizResultItem(com.student.aistudybuddy.data.model.QuizResult result) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyChatsState() {
    }
    
    private static final java.lang.String snippet(java.lang.String text) {
        return null;
    }
    
    private static final java.lang.String relativeTime(long timestamp) {
        return null;
    }
}