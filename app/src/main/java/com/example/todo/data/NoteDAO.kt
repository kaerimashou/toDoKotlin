package com.example.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todo.models.Note

@Dao
interface NoteDAO {
    @Query("SELECT * FROM note")
    fun getAll():List<Note>

    @Query("SELECT * FROM note")
    fun getAllLiveData():LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE id IN (:noteIDs)")
    fun loadAllById(noteIDs:IntArray):List<Note>

    @Query("SELECT * FROM note WHERE id=:id LIMIT 1")
    fun findById(id:Int):Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update
    fun update(note:Note)

    @Delete
    fun delete(user: Note)
}