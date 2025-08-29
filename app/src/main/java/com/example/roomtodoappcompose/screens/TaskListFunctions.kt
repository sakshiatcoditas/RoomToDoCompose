package com.example.roomtodoappcompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.roomtodoappcompose.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    var editingTaskId by remember { mutableStateOf<Int?>(null) }
    var editedText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task List") }
                // Back button removed; bottom nav handles navigation
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tasks) { task ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        if (editingTaskId == task.id) {
                            // Editing state
                            OutlinedTextField(
                                value = editedText,
                                onValueChange = { editedText = it },
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = {
                                    if (editedText.isNotBlank()) {
                                        viewModel.updateTask(task.copy(title = editedText))
                                        editingTaskId = null
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = "Save")
                            }

                        } else {
                            // Display state
                            Text(
                                text = task.title,
                                modifier = Modifier.weight(1f)
                            )
                            Row {
                                IconButton(
                                    onClick = {
                                        editingTaskId = task.id
                                        editedText = task.title
                                    }
                                ) {
                                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                                }
                                IconButton(onClick = { viewModel.deleteTask(task) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
