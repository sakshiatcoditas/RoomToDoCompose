package com.example.roomtodoappcompose.viewmodel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomtodoappcompose.TaskApp
import com.example.roomtodoappcompose.data.Task
import com.example.roomtodoappcompose.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    val tasks: StateFlow<List<Task>> = repository.allTasks
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addTask(title: String) {
        viewModelScope.launch {
            repository.insert(Task(title = title))
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.update(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    companion object {

        val Factory = viewModelFactory {
            initializer {
                // Get Application object (cast to TaskApp)
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TaskApp
                // Pass repository from TaskApp
                TaskViewModel(application.repository)
                //factory supplies the repository so the ViewModel can use it
            }
        }
    }
}
