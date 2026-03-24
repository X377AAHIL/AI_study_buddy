package com.student.aistudybuddy.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.aistudybuddy.data.local.AppDatabase
import com.student.aistudybuddy.data.model.ChatMessage
import com.student.aistudybuddy.data.model.ChatSession
import com.student.aistudybuddy.data.repository.GeminiRepository
import com.student.aistudybuddy.utils.TTSManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatViewModel(
    private val db: AppDatabase,
    appContext: Context,
) : ViewModel() {
    val messages: SnapshotStateList<ChatMessage> = mutableStateListOf()
    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)

    val isSpeaking: StateFlow<Boolean>
        get() = ttsManager.isSpeaking

    private val _speakingMessage = MutableStateFlow<String?>(null)
    val speakingMessage: StateFlow<String?> = _speakingMessage.asStateFlow()

    val chatHistory: StateFlow<List<ChatSession>> =
        db.chatDao().getAllSessions().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )

    private val repository = GeminiRepository()
    private val ttsManager = TTSManager(appContext.applicationContext)

    init {
        viewModelScope.launch {
            ttsManager.isSpeaking.collectLatest { speaking ->
                if (!speaking) {
                    _speakingMessage.value = null
                }
            }
        }
    }

    fun sendMessage(userText: String) {
        val trimmedMessage = userText.trim()
        if (trimmedMessage.isEmpty()) return

        messages.add(ChatMessage(text = trimmedMessage, isUser = true))
        isLoading.value = true

        viewModelScope.launch {
            try {
                val result = repository.chat(trimmedMessage)
                if (result.isSuccess) {
                    val response = result.getOrNull().orEmpty()
                    messages.add(ChatMessage(text = response, isUser = false))

                    val topic = messages
                        .firstOrNull { it.isUser }
                        ?.text
                        ?.trim()
                        ?.takeIf { it.isNotBlank() }
                        ?: "General Chat"

                    db.chatDao().insertSession(
                        ChatSession(
                            topic = topic,
                            summary = response.take(100),
                            timestamp = System.currentTimeMillis(),
                        ),
                    )
                } else {
                    val rootCause = result.exceptionOrNull()?.message
                        ?.replace("\n", " ")
                        ?.take(140)
                        ?.trim()

                    errorMessage.value = if (rootCause.isNullOrBlank()) {
                        "Failed to get response. Try again."
                    } else {
                        "Failed to get response: $rootCause"
                    }
                }
            } finally {
                isLoading.value = false
            }
        }
    }

    fun clearError() {
        errorMessage.value = null
    }

    fun clearChat() {
        messages.clear()
        repository.resetChat()
    }

    fun toggleSpeak(text: String) {
        if (ttsManager.isSpeaking.value) {
            ttsManager.stop()
            _speakingMessage.value = null
        } else {
            _speakingMessage.value = text
            ttsManager.speak(text)
        }
    }

    override fun onCleared() {
        ttsManager.shutdown()
        super.onCleared()
    }
}
