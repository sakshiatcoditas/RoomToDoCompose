package com.example.roomtodoappcompose

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object TaskList : Screen("task_list")
}
