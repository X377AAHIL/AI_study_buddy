package com.student.aistudybuddy.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.student.aistudybuddy.data.model.ChatSession
import com.student.aistudybuddy.data.model.QuizResult

@Database(entities = [ChatSession::class, QuizResult::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
    abstract fun quizDao(): QuizDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "aistudybuddy_database",
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
