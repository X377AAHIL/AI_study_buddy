package com.student.aistudybuddy.viewmodel;

import androidx.lifecycle.ViewModel;
import com.student.aistudybuddy.data.local.AppDatabase;
import com.student.aistudybuddy.data.model.QuizQuestion;
import com.student.aistudybuddy.data.model.QuizResult;
import com.student.aistudybuddy.data.repository.GeminiRepository;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/student/aistudybuddy/viewmodel/QuizUiState;", "", "()V", "InProgress", "Loading", "Results", "TopicPicker", "Lcom/student/aistudybuddy/viewmodel/QuizUiState$InProgress;", "Lcom/student/aistudybuddy/viewmodel/QuizUiState$Loading;", "Lcom/student/aistudybuddy/viewmodel/QuizUiState$Results;", "Lcom/student/aistudybuddy/viewmodel/QuizUiState$TopicPicker;", "app_debug"})
public abstract class QuizUiState {
    
    private QuizUiState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\t\u0010\u0019\u001a\u00020\nH\u00c6\u0003JH\u0010\u001a\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u00d6\u0003J\t\u0010\u001f\u001a\u00020\u0006H\u00d6\u0001J\t\u0010 \u001a\u00020!H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000eR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\""}, d2 = {"Lcom/student/aistudybuddy/viewmodel/QuizUiState$InProgress;", "Lcom/student/aistudybuddy/viewmodel/QuizUiState;", "questions", "", "Lcom/student/aistudybuddy/data/model/QuizQuestion;", "currentIndex", "", "score", "selectedAnswer", "isAnswerRevealed", "", "(Ljava/util/List;IILjava/lang/Integer;Z)V", "getCurrentIndex", "()I", "()Z", "getQuestions", "()Ljava/util/List;", "getScore", "getSelectedAnswer", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/util/List;IILjava/lang/Integer;Z)Lcom/student/aistudybuddy/viewmodel/QuizUiState$InProgress;", "equals", "other", "", "hashCode", "toString", "", "app_debug"})
    public static final class InProgress extends com.student.aistudybuddy.viewmodel.QuizUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.student.aistudybuddy.data.model.QuizQuestion> questions = null;
        private final int currentIndex = 0;
        private final int score = 0;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer selectedAnswer = null;
        private final boolean isAnswerRevealed = false;
        
        public InProgress(@org.jetbrains.annotations.NotNull()
        java.util.List<com.student.aistudybuddy.data.model.QuizQuestion> questions, int currentIndex, int score, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedAnswer, boolean isAnswerRevealed) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.student.aistudybuddy.data.model.QuizQuestion> getQuestions() {
            return null;
        }
        
        public final int getCurrentIndex() {
            return 0;
        }
        
        public final int getScore() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getSelectedAnswer() {
            return null;
        }
        
        public final boolean isAnswerRevealed() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.student.aistudybuddy.data.model.QuizQuestion> component1() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int component3() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        public final boolean component5() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.student.aistudybuddy.viewmodel.QuizUiState.InProgress copy(@org.jetbrains.annotations.NotNull()
        java.util.List<com.student.aistudybuddy.data.model.QuizQuestion> questions, int currentIndex, int score, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedAnswer, boolean isAnswerRevealed) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/student/aistudybuddy/viewmodel/QuizUiState$Loading;", "Lcom/student/aistudybuddy/viewmodel/QuizUiState;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class Loading extends com.student.aistudybuddy.viewmodel.QuizUiState {
        @org.jetbrains.annotations.NotNull()
        public static final com.student.aistudybuddy.viewmodel.QuizUiState.Loading INSTANCE = null;
        
        private Loading() {
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lcom/student/aistudybuddy/viewmodel/QuizUiState$Results;", "Lcom/student/aistudybuddy/viewmodel/QuizUiState;", "score", "", "total", "topic", "", "(IILjava/lang/String;)V", "getScore", "()I", "getTopic", "()Ljava/lang/String;", "getTotal", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_debug"})
    public static final class Results extends com.student.aistudybuddy.viewmodel.QuizUiState {
        private final int score = 0;
        private final int total = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String topic = null;
        
        public Results(int score, int total, @org.jetbrains.annotations.NotNull()
        java.lang.String topic) {
        }
        
        public final int getScore() {
            return 0;
        }
        
        public final int getTotal() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTopic() {
            return null;
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.student.aistudybuddy.viewmodel.QuizUiState.Results copy(int score, int total, @org.jetbrains.annotations.NotNull()
        java.lang.String topic) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/student/aistudybuddy/viewmodel/QuizUiState$TopicPicker;", "Lcom/student/aistudybuddy/viewmodel/QuizUiState;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class TopicPicker extends com.student.aistudybuddy.viewmodel.QuizUiState {
        @org.jetbrains.annotations.NotNull()
        public static final com.student.aistudybuddy.viewmodel.QuizUiState.TopicPicker INSTANCE = null;
        
        private TopicPicker() {
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}