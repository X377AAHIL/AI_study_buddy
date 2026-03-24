package com.student.aistudybuddy.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.student.aistudybuddy.data.model.QuizResult;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/student/aistudybuddy/data/local/QuizDao;", "", "getAllResults", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/student/aistudybuddy/data/model/QuizResult;", "insertResult", "", "result", "(Lcom/student/aistudybuddy/data/model/QuizResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface QuizDao {
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertResult(@org.jetbrains.annotations.NotNull()
    com.student.aistudybuddy.data.model.QuizResult result, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM QuizResult ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.student.aistudybuddy.data.model.QuizResult>> getAllResults();
}