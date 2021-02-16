package com.vimalnaina.mynote.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vimalnaina.mynote.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note : Note)

    @Query("SELECT * FROM Note WHERE uid = :uid ORDER BY noteId DESC")
    fun getAllNotes(uid : Int) : LiveData<List<Note>>

    @Delete
    suspend fun deleteNote(note : Note)

    @Query("DELETE FROM Note")
    suspend fun deleteAllNotes()

    @Update
    suspend fun updateNote(note : Note)
}