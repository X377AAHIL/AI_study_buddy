package com.student.aistudybuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.student.aistudybuddy.data.model.ChatMessage
import com.student.aistudybuddy.ui.components.LoadingMessageBubble
import com.student.aistudybuddy.ui.components.MessageBubble
import com.student.aistudybuddy.viewmodel.ChatViewModel

@Composable
fun ChatScreen(
    viewModelFactory: ViewModelProvider.Factory,
) {
    val viewModel: ChatViewModel = viewModel(factory = viewModelFactory)
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isSpeaking by viewModel.isSpeaking.collectAsState()
    val speakingMessage by viewModel.speakingMessage.collectAsState()

    ChatScreenContent(
        messages = viewModel.messages,
        isLoading = isLoading,
        errorMessage = errorMessage,
        isSpeaking = isSpeaking,
        speakingMessage = speakingMessage,
        onSend = viewModel::sendMessage,
        onSpeak = viewModel::toggleSpeak,
        onErrorConsumed = viewModel::clearError,
    )
}

@Composable
private fun ChatScreenContent(
    messages: List<ChatMessage>,
    isLoading: Boolean,
    errorMessage: String?,
    isSpeaking: Boolean,
    speakingMessage: String?,
    onSend: (String) -> Unit,
    onSpeak: (String) -> Unit,
    onErrorConsumed: () -> Unit,
) {
    var inputText by rememberSaveable { mutableStateOf("") }
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }
    val totalItems = messages.size + if (isLoading) 1 else 0

    LaunchedEffect(totalItems) {
        if (totalItems > 0) {
            listState.animateScrollToItem(totalItems - 1)
        }
    }

    LaunchedEffect(errorMessage) {
        val message = errorMessage ?: return@LaunchedEffect
        snackbarHostState.showSnackbar(message)
        onErrorConsumed()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            InputBar(
                value = inputText,
                onValueChange = { inputText = it },
                onSendClick = {
                    val text = inputText.trim()
                    if (text.isNotEmpty()) {
                        onSend(text)
                        inputText = ""
                    }
                },
                enabled = !isLoading,
            )
        },
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = messages,
                key = { it.id },
            ) { message ->
                AnimatedMessageBubble(
                    message = message,
                    isSpeaking = !message.isUser && isSpeaking && speakingMessage == message.text,
                    onSpeak = onSpeak,
                )
            }

            if (isLoading) {
                item {
                    LoadingMessageBubble()
                }
            }
        }
    }
}

@Composable
private fun AnimatedMessageBubble(
    message: ChatMessage,
    isSpeaking: Boolean,
    onSpeak: (String) -> Unit,
) {
    var visible by remember(message.id) { mutableStateOf(false) }

    LaunchedEffect(message.id) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(durationMillis = 280)) +
            slideInVertically(
                animationSpec = tween(durationMillis = 280),
                initialOffsetY = { fullHeight -> fullHeight / 2 },
            ),
    ) {
        MessageBubble(
            message = message,
            isSpeaking = isSpeaking,
            onSpeak = onSpeak,
        )
    }
}

@Composable
private fun InputBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit,
    enabled: Boolean,
) {
    Surface(
        tonalElevation = 2.dp,
        shadowElevation = 2.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .imePadding()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text("Ask anything...") },
                modifier = Modifier.weight(1f),
                enabled = enabled,
                shape = RoundedCornerShape(24.dp),
                singleLine = false,
                maxLines = 4,
                textStyle = MaterialTheme.typography.bodyMedium,
            )

            IconButton(
                onClick = onSendClick,
                enabled = enabled && value.trim().isNotEmpty(),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Send",
                )
            }
        }
    }
}
