package com.example.roomtodoappcompose
import android.app.Application
import androidx.room.Room
import com.example.roomtodoappcompose.data.AppDatabase
import com.example.roomtodoappcompose.repository.TaskRepository
import kotlin.getValue

class TaskApp : Application() {
    val database by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "task_db").build()
    }

    val repository by lazy {
        TaskRepository(database.taskDao())
    }
}
