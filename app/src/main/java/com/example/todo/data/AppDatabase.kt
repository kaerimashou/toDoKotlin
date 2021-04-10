package com.example.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.models.Note

@Database(entities = [Note::class],version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val noteDao:NoteDAO

}