package com.student.aistudybuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.student.aistudybuddy.data.local.AppDatabase
import com.student.aistudybuddy.ui.theme.AIStudyBuddyTheme
import com.student.aistudybuddy.viewmodel.AppViewModelFactory
import com.student.aistudybuddy.viewmodel.HistoryViewModel

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getDatabase(applicationContext)
        val viewModelFactory = AppViewModelFactory(
            db = database,
            appContext = applicationContext,
        )

        setContent {
            AIStudyBuddyTheme {
                AIStudyBuddyApp(viewModelFactory = viewModelFactory)
            }
        }
    }
}

@Composable
private fun AIStudyBuddyApp(
    viewModelFactory: ViewModelProvider.Factory,
) {
    val navController = rememberNavController()
    val historyViewModel: HistoryViewModel = viewModel(factory = viewModelFactory)
    val quizResults by historyViewModel.quizResults.collectAsState()
    val hasHistoryBadge = quizResults.any { result ->
        result.total > 0 && (result.score * 100 / result.total) >= 80
    }

    val navItems = listOf(
        BottomNavItem(route = Routes.CHAT, label = "Chat", icon = Icons.Filled.ChatBubble),
        BottomNavItem(route = Routes.QUIZ, label = "Quiz", icon = Icons.Filled.Lightbulb),
        BottomNavItem(route = Routes.HISTORY, label = "History", icon = Icons.Filled.History),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                navItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            if (item.route == Routes.HISTORY && hasHistoryBadge) {
                                BadgedBox(
                                    badge = {
                                        Badge()
                                    },
                                ) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.label,
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label,
                                )
                            }
                        },
                        label = { Text(text = item.label) },
                    )
                }
            }
        },
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            innerPadding = innerPadding,
            viewModelFactory = viewModelFactory,
        )
    }
}
