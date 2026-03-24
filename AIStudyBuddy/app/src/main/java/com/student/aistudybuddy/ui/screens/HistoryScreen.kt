package com.student.aistudybuddy.ui.screens

import android.util.Log
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.student.aistudybuddy.data.model.ChatSession
import com.student.aistudybuddy.data.model.QuizResult
import com.student.aistudybuddy.viewmodel.HistoryViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryScreen(
    viewModelFactory: ViewModelProvider.Factory,
) {
    val viewModel: HistoryViewModel = viewModel(factory = viewModelFactory)
    val chatSessions by viewModel.chatSessions.collectAsStateWithLifecycle()
    val quizResults by viewModel.quizResults.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    HistoryScreenContent(
        isLoading = isLoading,
        chatSessions = chatSessions,
        quizResults = quizResults,
        onChatClick = { session ->
            Log.d("HistoryScreen", "Chat clicked: id=${session.id}, topic=${session.topic}")
        },
        onDeleteChat = viewModel::deleteSession,
    )
}

@Composable
private fun HistoryScreenContent(
    isLoading: Boolean,
    chatSessions: List<ChatSession>,
    quizResults: List<QuizResult>,
    onChatClick: (ChatSession) -> Unit,
    onDeleteChat: (ChatSession) -> Unit,
) {
    if (isLoading) {
        HistoryLoadingContent()
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            SectionHeader(title = "Recent Chats")
        }

        if (chatSessions.isEmpty()) {
            item {
                EmptyChatsState()
            }
        } else {
            items(
                items = chatSessions,
                key = { it.id },
            ) { chat ->
                DismissibleChatSessionItem(
                    session = chat,
                    onChatClick = onChatClick,
                    onDeleteChat = onDeleteChat,
                )
            }
        }

        item {
            SectionHeader(title = "Quiz Results")
        }

        if (quizResults.isEmpty()) {
            item {
                Text(
                    text = "No quiz results yet.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        } else {
            items(
                items = quizResults,
                key = { it.id },
            ) { result ->
                QuizResultItem(result = result)
            }
        }
    }
}

@Composable
private fun HistoryLoadingContent() {
    val shimmerTransition = rememberInfiniteTransition(label = "history_shimmer_transition")
    val shimmerAlpha by shimmerTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "history_shimmer_alpha",
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(3) {
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(18.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = shimmerAlpha)),
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(14.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = shimmerAlpha)),
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.35f)
                            .height(12.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = shimmerAlpha)),
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DismissibleChatSessionItem(
    session: ChatSession,
    onChatClick: (ChatSession) -> Unit,
    onDeleteChat: (ChatSession) -> Unit,
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.StartToEnd || value == SwipeToDismissBoxValue.EndToStart) {
                onDeleteChat(session)
                true
            } else {
                false
            }
        },
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            val isDismissDirection =
                dismissState.dismissDirection == SwipeToDismissBoxValue.StartToEnd ||
                    dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isDismissDirection) Color(0xFFC62828) else Color.Transparent)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterEnd,
            ) {
                Text(
                    text = "Delete",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        },
    ) {
        ChatSessionItem(
            session = session,
            onChatClick = onChatClick,
        )
    }
}

@Composable
private fun ChatSessionItem(
    session: ChatSession,
    onChatClick: (ChatSession) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onChatClick(session)
            },
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = session.topic,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = snippet(session.summary),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = relativeTime(session.timestamp),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun QuizResultItem(result: QuizResult) {
    val percentage = if (result.total == 0) 0 else (result.score * 100) / result.total
    val badgeColor = when {
        percentage >= 80 -> Color(0xFF2E7D32)
        percentage >= 50 -> Color(0xFFF9A825)
        else -> Color(0xFFC62828)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = result.topic,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = relativeTime(result.timestamp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Surface(
                shape = RoundedCornerShape(999.dp),
                color = badgeColor.copy(alpha = 0.16f),
            ) {
                Text(
                    text = "${result.score} / ${result.total}",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.labelLarge,
                    color = badgeColor,
                )
            }
        }
    }
}

@Composable
private fun EmptyChatsState() {
    val outlineColor = MaterialTheme.colorScheme.outline

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(120.dp)
                .clip(RoundedCornerShape(12.dp))
                .drawBehind {
                    val strokeWidth = 2.dp.toPx()
                    drawRoundRect(
                        color = outlineColor,
                        style = Stroke(
                            width = strokeWidth,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 12f), 0f),
                            cap = StrokeCap.Round,
                        ),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(12.dp.toPx()),
                    )
                },
        )

        Text(
            text = "No chats yet. Start studying!",
            modifier = Modifier.padding(top = 12.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

private fun snippet(text: String): String {
    val trimmed = text.trim()
    if (trimmed.length <= 90) return trimmed
    return "${trimmed.take(87)}..."
}

private fun relativeTime(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = (now - timestamp).coerceAtLeast(0L)

    val minute = 60_000L
    val hour = 60 * minute
    val day = 24 * hour

    return when {
        diff < minute -> "just now"
        diff < hour -> {
            val minutes = diff / minute
            "$minutes minute${if (minutes == 1L) "" else "s"} ago"
        }

        diff < day -> {
            val hours = diff / hour
            "$hours hour${if (hours == 1L) "" else "s"} ago"
        }

        diff < 7 * day -> {
            val days = diff / day
            "$days day${if (days == 1L) "" else "s"} ago"
        }

        else -> {
            val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            formatter.format(Date(timestamp))
        }
    }
}
