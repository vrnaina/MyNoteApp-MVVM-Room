package com.vimalnaina.mynote.repository

import androidx.lifecycle.LiveData
import com.vimalnaina.mynote.db.NoteDatabase
import com.vimalnaina.mynote.model.Note

class NoteRepository(val db:NoteDatabase) {
    suspend fun addNote(note: Note){
        db.getNoteDao().addNote(note)
    }

    suspend fun updateNote(note: Note){
        db.getNoteDao().updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        db.getNoteDao().deleteNote(note)
    }

    suspend fun deleteAllNotes(){
        db.getNoteDao().deleteAllNotes()
    }

    fun getAllNotes(uid: Int): LiveData<List<Note>>{
        return db.getNoteDao().getAllNotes(uid)
    }
}