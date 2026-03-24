package com.student.aistudybuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.aistudybuddy.data.local.AppDatabase
import com.student.aistudybuddy.data.model.ChatSession
import com.student.aistudybuddy.data.model.QuizResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val db: AppDatabase,
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val chatSessions: StateFlow<List<ChatSession>> =
        db.chatDao().getAllSessions().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )

    val quizResults: StateFlow<List<QuizResult>> =
        db.quizDao().getAllResults().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )

    init {
        viewModelScope.launch {
            combine(chatSessions, quizResults) { _, _ -> Unit }.first()
            _isLoading.value = false
        }
    }

    fun deleteSession(session: ChatSession) {
        viewModelScope.launch {
            db.chatDao().deleteSession(session)
        }
    }
}
