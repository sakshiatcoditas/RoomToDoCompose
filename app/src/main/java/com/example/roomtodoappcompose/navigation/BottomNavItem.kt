package com.example.roomtodoappcompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.roomtodoappcompose.Screen

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavItem(Screen.Home.route, "Home", Icons.Default.Home)
    object Tasks : BottomNavItem(Screen.TaskList.route, "Tasks", Icons.AutoMirrored.Filled.List)
}