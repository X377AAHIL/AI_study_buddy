package com.student.aistudybuddy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.student.aistudybuddy.data.local.AppDatabase

class AppViewModelFactory(
    private val db: AppDatabase,
    private val appContext: Context,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ChatViewModel::class.java) -> ChatViewModel(db, appContext) as T
            modelClass.isAssignableFrom(QuizViewModel::class.java) -> QuizViewModel(db) as T
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> HistoryViewModel(db) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
