package com.student.aistudybuddy

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.student.aistudybuddy.ui.screens.ChatScreen
import com.student.aistudybuddy.ui.screens.HistoryScreen
import com.student.aistudybuddy.ui.screens.QuizScreen

object Routes {
    const val CHAT = "chat"
    const val QUIZ = "quiz"
    const val HISTORY = "history"
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModelFactory: ViewModelProvider.Factory,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.CHAT,
        modifier = Modifier.padding(innerPadding),
    ) {
        composable(Routes.CHAT) {
            ChatScreen(viewModelFactory = viewModelFactory)
        }

        composable(Routes.QUIZ) {
            QuizScreen(viewModelFactory = viewModelFactory)
        }

        composable(Routes.HISTORY) {
            HistoryScreen(viewModelFactory = viewModelFactory)
        }
    }
}
