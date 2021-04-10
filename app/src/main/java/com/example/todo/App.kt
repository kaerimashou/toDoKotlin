package com.example.todo

import android.app.Application
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.todo.data.AppDatabase
import com.example.todo.data.NoteDAO

class App: Application() {
    private lateinit var db:AppDatabase
        private set

    lateinit var noteDAO: NoteDAO
        private set


    companion object{
        @JvmStatic
        private lateinit var instance:App

        fun getInstance(): App {
            return instance
        }
    }


    override fun onCreate() {
        super.onCreate()
        db=databaseBuilder(applicationContext,AppDatabase::class.java,"db-app").allowMainThreadQueries().build()
        instance=this
        noteDAO=db.noteDao

    }
}