package com.example.roomtodoappcompose.repository

import com.example.roomtodoappcompose.data.Task
import com.example.roomtodoappcompose.data.TaskDao

class TaskRepository(private val dao: TaskDao) {
    val allTasks = dao.getAllTasks()

    //dao has "getAllTasks()" that is been stored in "allTasks" in repository & in the viewmodel it
    // is stored into "tasks"

    suspend fun insert(task: Task) = dao.insert(task)
    suspend fun update(task: Task) = dao.update(task)
    suspend fun delete(task: Task) = dao.delete(task)
}