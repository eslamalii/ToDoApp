package com.example.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todo.data.models.ToDoData

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_data ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    @Update
    suspend fun updateData(toDoData: ToDoData)

    @Delete
    suspend fun deleteData(toDoData: ToDoData)

    @Query("DELETE FROM todo_data")
    suspend fun deleteAll()

}