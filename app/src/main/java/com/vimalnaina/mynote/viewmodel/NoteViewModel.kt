package com.vimalnaina.mynote.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vimalnaina.mynote.model.Note
import com.vimalnaina.mynote.repository.NoteRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository): ViewModel() {
    fun addNote(note: Note) = viewModelScope.launch{
        noteRepository.addNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch{
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun deleteAllNotes() = viewModelScope.launch{
        noteRepository.deleteAllNotes()
    }

    fun getAllNotes(uid: Int) : LiveData<List<Note>>{
        return noteRepository.getAllNotes(uid)
    }
}