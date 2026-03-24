package com.student.aistudybuddy.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.student.aistudybuddy.data.model.ChatSession
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Insert
    suspend fun insertSession(session: ChatSession)

    @Query("SELECT * FROM ChatSession ORDER BY timestamp DESC")
    fun getAllSessions(): Flow<List<ChatSession>>

    @Delete
    suspend fun deleteSession(session: ChatSession)
}
