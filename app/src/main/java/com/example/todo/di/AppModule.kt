package com.example.todo.di

import android.content.Context
import androidx.room.Room
import com.example.todo.data.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideToDoDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        ToDoDatabase::class.java,
        "todo_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideTodoDao(db: ToDoDatabase) = db.toDoDao()

}