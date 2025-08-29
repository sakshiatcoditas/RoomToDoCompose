package com.example.roomtodoappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomtodoappcompose.navigation.BottomNavigationBar
import com.example.roomtodoappcompose.screens.HomeScreen
import com.example.roomtodoappcompose.screens.TaskListScreen
import com.example.roomtodoappcompose.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Grab repository from Application
        val repository = (application as TaskApp).repository

        // Define ViewModel factory in DSL style
        val factory = viewModelFactory {
            initializer {
                TaskViewModel(repository)
            }
        }

        setContent {
            val taskViewModel: TaskViewModel = viewModel(factory = factory)
            AppNavigation(taskViewModel)
        }
    }
}

@Composable
fun AppNavigation(taskViewModel: TaskViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onAddTask = { taskTitle ->
                        taskViewModel.addTask(taskTitle)
                        navController.navigate(Screen.TaskList.route)
                    }
                )
            }
            composable(Screen.TaskList.route) {
                TaskListScreen(viewModel = taskViewModel)
            }
        }
    }
}
