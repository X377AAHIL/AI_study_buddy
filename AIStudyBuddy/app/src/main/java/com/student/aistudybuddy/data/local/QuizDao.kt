package com.student.aistudybuddy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.student.aistudybuddy.data.model.QuizResult
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Insert
    suspend fun insertResult(result: QuizResult)

    @Query("SELECT * FROM QuizResult ORDER BY timestamp DESC")
    fun getAllResults(): Flow<List<QuizResult>>
}
