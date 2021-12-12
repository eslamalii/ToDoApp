package com.example.todo.data

import androidx.room.TypeConverter
import com.example.todo.data.models.Priority
import java.util.*

class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(data: Date?): Long? {
        return data?.time
    }
}