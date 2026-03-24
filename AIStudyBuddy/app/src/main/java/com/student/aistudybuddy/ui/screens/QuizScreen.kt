package com.student.aistudybuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.student.aistudybuddy.ui.components.QuizCard
import com.student.aistudybuddy.viewmodel.QuizUiState
import com.student.aistudybuddy.viewmodel.QuizViewModel
import kotlinx.coroutines.flow.collectLatest

private val SuggestedTopics = listOf(
    "Photosynthesis",
    "World War II",
    "Python basics",
)

@Composable
fun QuizScreen(
    viewModelFactory: ViewModelProvider.Factory,
) {
    val viewModel: QuizViewModel = viewModel(factory = viewModelFactory)
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.errorEvents.collectLatest { error ->
            snackbarHostState.showSnackbar(error)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        when (val state = uiState) {
            QuizUiState.Loading -> LoadingState(modifier = Modifier.padding(innerPadding))

            QuizUiState.TopicPicker -> TopicPickerState(
                modifier = Modifier.padding(innerPadding),
                onGenerateQuiz = viewModel::generateQuiz,
            )

            is QuizUiState.InProgress -> InProgressState(
                modifier = Modifier.padding(innerPadding),
                uiState = state,
                onAnswerSelected = viewModel::selectAnswer,
            )

            is QuizUiState.Results -> ResultsState(
                modifier = Modifier.padding(innerPadding),
                uiState = state,
                onRetry = viewModel::resetQuiz,
            )
        }
    }
}

@Composable
private fun TopicPickerState(
    modifier: Modifier = Modifier,
    onGenerateQuiz: (String) -> Unit,
) {
    var topicInput by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text(
            text = "Choose a quiz topic",
            style = MaterialTheme.typography.headlineSmall,
        )

        TextField(
            value = topicInput,
            onValueChange = { topicInput = it },
            placeholder = { Text("Enter topic") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Button(
            onClick = { onGenerateQuiz(topicInput.trim()) },
            modifier = Modifier.fillMaxWidth(),
            enabled = topicInput.trim().isNotEmpty(),
        ) {
            Text("Generate Quiz")
        }

        Text(
            text = "Suggested topics",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(SuggestedTopics) { topic ->
                AssistChip(
                    onClick = { topicInput = topic },
                    label = { Text(topic) },
                )
            }
        }
    }
}

@Composable
private fun InProgressState(
    modifier: Modifier = Modifier,
    uiState: QuizUiState.InProgress,
    onAnswerSelected: (Int) -> Unit,
) {
    val question = uiState.questions[uiState.currentIndex]
    val haptic = LocalHapticFeedback.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        QuizCard(
            questionNumber = uiState.currentIndex + 1,
            totalQuestions = uiState.questions.size,
            questionText = question.question,
            options = question.options,
            selectedAnswerIndex = uiState.selectedAnswer,
            correctAnswerIndex = question.correctAnswerIndex,
            onOptionSelected = { answerIndex ->
                if (!uiState.isAnswerRevealed) {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onAnswerSelected(answerIndex)
                }
            },
        )
    }
}

@Composable
private fun ResultsState(
    modifier: Modifier = Modifier,
    uiState: QuizUiState.Results,
    onRetry: () -> Unit,
) {
    val progress = if (uiState.total == 0) 0f else uiState.score.toFloat() / uiState.total.toFloat()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Text(
            text = "Your Score",
            style = MaterialTheme.typography.titleLarge,
        )

        Text(
            text = "${uiState.score} / ${uiState.total}",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary,
        )

        LinearProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = Modifier.fillMaxWidth(),
        )

        Text(
            text = motivationalMessage(progress),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )

        Button(
            onClick = onRetry,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp),
        ) {
            Text("Try Again")
        }
    }
}

@Composable
private fun LoadingState(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
        Text(text = "Generating quiz...")
    }
}

private fun motivationalMessage(progress: Float): String {
    return when {
        progress >= 0.8f -> "Excellent work! You are mastering this topic."
        progress >= 0.5f -> "Nice effort! A quick review and you will improve fast."
        else -> "Good start! Keep practicing and you will get there."
    }
}
