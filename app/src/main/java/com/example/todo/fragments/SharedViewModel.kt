package com.example.todo.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todo.data.models.Priority
import com.example.todo.data.models.ToDoData


class SharedViewModel(application: Application) : AndroidViewModel(application) {

    var emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkDatabaseEmpty(data: List<ToDoData>) {
        emptyDatabase.value = data.isEmpty()
    }

    fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }

    fun parsePriority(priority: String): Priority {
        return when (priority) {
            "High Priority" -> Priority.HIGH
            "Medium Priority" -> Priority.MEDIUM
            "Low Priority" -> Priority.LOW
            else -> Priority.LOW
        }
    }

}