package com.student.aistudybuddy.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val CorrectAnswerColor = Color(0xFF2E7D32)
private val WrongAnswerColor = Color(0xFFC62828)

@Composable
fun QuizCard(
    questionNumber: Int,
    totalQuestions: Int,
    questionText: String,
    options: List<String>,
    selectedAnswerIndex: Int?,
    correctAnswerIndex: Int,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Text(
                text = "Question $questionNumber of $totalQuestions",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
            )

            Text(
                text = questionText,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )

            options.take(4).forEachIndexed { index, option ->
                val isAnswered = selectedAnswerIndex != null
                val isCorrect = index == correctAnswerIndex
                val isWrongSelection = selectedAnswerIndex == index && !isCorrect

                val containerColor = when {
                    !isAnswered -> MaterialTheme.colorScheme.primaryContainer
                    isCorrect -> CorrectAnswerColor
                    isWrongSelection -> WrongAnswerColor
                    else -> MaterialTheme.colorScheme.surfaceVariant
                }
                val animatedContainerColor by animateColorAsState(
                    targetValue = containerColor,
                    animationSpec = tween(durationMillis = 400),
                    label = "quiz_option_bg",
                )

                val contentColor = when {
                    isCorrect || isWrongSelection -> Color.White
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }

                Button(
                    onClick = { onOptionSelected(index) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isAnswered,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = animatedContainerColor,
                        contentColor = contentColor,
                        disabledContainerColor = animatedContainerColor,
                        disabledContentColor = contentColor,
                    ),
                ) {
                    Text(
                        text = option,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}
