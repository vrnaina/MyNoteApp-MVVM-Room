package com.vimalnaina.mynote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vimalnaina.mynote.repository.NoteRepository

class NoteViewModelProviderFactory(val noteRepository: NoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteViewModel(noteRepository) as T
    }
}