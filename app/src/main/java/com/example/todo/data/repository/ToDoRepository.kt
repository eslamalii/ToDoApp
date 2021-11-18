package com.example.todo.data.repository

import androidx.lifecycle.LiveData
import com.example.todo.data.ToDoDao
import com.example.todo.data.models.ToDoData
import javax.inject.Inject

class ToDoRepository @Inject constructor(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }

    suspend fun updateData(toDoData: ToDoData) {
        toDoDao.updateData(toDoData)
    }

    suspend fun deleteData(toDoData: ToDoData) {
        toDoDao.deleteData(toDoData)
    }

    suspend fun deleteAll() {
        toDoDao.deleteAll()
    }
}