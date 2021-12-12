package com.example.todo.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.models.ToDoData
import com.example.todo.data.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    val getAllData: LiveData<List<ToDoData>> = repository.getAllData
    val sortByHigh: LiveData<List<ToDoData>> = repository.sortByHigh()
    val sortByLow: LiveData<List<ToDoData>> = repository.sortByLow()

    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

    fun updateData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }

    fun deleteData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(toDoData)
        }
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) { repository.deleteAll() }

    fun searchDatabase(search: String): LiveData<List<ToDoData>> {
        return repository.searchDatabase(search)
    }
}