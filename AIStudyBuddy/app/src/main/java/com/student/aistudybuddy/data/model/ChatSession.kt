package com.student.aistudybuddy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ChatSession")
data class ChatSession(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val topic: String,
    val summary: String,
    val timestamp: Long = System.currentTimeMillis(),
)
