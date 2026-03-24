package com.student.aistudybuddy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "QuizResult")
data class QuizResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val topic: String,
    val score: Int,
    val total: Int,
    val timestamp: Long = System.currentTimeMillis(),
)
