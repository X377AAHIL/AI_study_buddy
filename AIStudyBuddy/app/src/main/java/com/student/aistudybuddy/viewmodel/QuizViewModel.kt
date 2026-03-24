package com.student.aistudybuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.aistudybuddy.data.local.AppDatabase
import com.student.aistudybuddy.data.model.QuizQuestion
import com.student.aistudybuddy.data.model.QuizResult
import com.student.aistudybuddy.data.repository.GeminiRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class QuizUiState {
    data object TopicPicker : QuizUiState()

    data object Loading : QuizUiState()

    data class InProgress(
        val questions: List<QuizQuestion>,
        val currentIndex: Int,
        val score: Int,
        val selectedAnswer: Int?,
        val isAnswerRevealed: Boolean,
    ) : QuizUiState()

    data class Results(
        val score: Int,
        val total: Int,
        val topic: String,
    ) : QuizUiState()
}

class QuizViewModel(
    private val db: AppDatabase,
) : ViewModel() {
    val uiState: MutableStateFlow<QuizUiState> = MutableStateFlow(QuizUiState.TopicPicker)
    val errorEvents: MutableSharedFlow<String> = MutableSharedFlow(extraBufferCapacity = 1)

    val quizHistory: StateFlow<List<QuizResult>> =
        db.quizDao().getAllResults().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )

    private val repository = GeminiRepository()
    private var currentTopic: String = ""

    fun generateQuiz(topic: String) {
        val trimmedTopic = topic.trim()
        if (trimmedTopic.isEmpty()) {
            errorEvents.tryEmit("Please enter a topic first.")
            return
        }

        currentTopic = trimmedTopic
        uiState.value = QuizUiState.Loading

        viewModelScope.launch {
            repository.generateQuiz(trimmedTopic)
                .onSuccess { result ->
                    if (result.isEmpty()) {
                        uiState.value = QuizUiState.TopicPicker
                        errorEvents.emit("Could not generate a valid quiz. Try another topic.")
                    } else {
                        uiState.value = QuizUiState.InProgress(
                            questions = result,
                            currentIndex = 0,
                            score = 0,
                            selectedAnswer = null,
                            isAnswerRevealed = false,
                        )
                    }
                }
                .onFailure {
                    uiState.value = QuizUiState.TopicPicker
                    errorEvents.emit("Failed to generate quiz. Please try again.")
                }
        }
    }

    fun selectAnswer(answerIndex: Int) {
        val currentState = uiState.value as? QuizUiState.InProgress ?: return
        if (currentState.isAnswerRevealed) return

        val currentQuestion = currentState.questions.getOrNull(currentState.currentIndex) ?: return
        val updatedScore = if (answerIndex == currentQuestion.correctAnswerIndex) {
            currentState.score + 1
        } else {
            currentState.score
        }

        uiState.value = currentState.copy(
            score = updatedScore,
            selectedAnswer = answerIndex,
            isAnswerRevealed = true,
        )

        viewModelScope.launch {
            delay(1200)

            val latestState = uiState.value as? QuizUiState.InProgress ?: return@launch
            if (!latestState.isAnswerRevealed) return@launch

            val nextIndex = latestState.currentIndex + 1
            if (nextIndex >= latestState.questions.size) {
                db.quizDao().insertResult(
                    QuizResult(
                        topic = currentTopic,
                        score = latestState.score,
                        total = latestState.questions.size,
                    ),
                )

                uiState.value = QuizUiState.Results(
                    score = latestState.score,
                    total = latestState.questions.size,
                    topic = currentTopic,
                )
            } else {
                uiState.value = latestState.copy(
                    currentIndex = nextIndex,
                    selectedAnswer = null,
                    isAnswerRevealed = false,
                )
            }
        }
    }

    fun resetQuiz() {
        uiState.value = QuizUiState.TopicPicker
    }
}
