package com.vimalnaina.mynote.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.vimalnaina.mynote.R
import com.vimalnaina.mynote.model.Note
import com.vimalnaina.mynote.ui.MainActivity
import com.vimalnaina.mynote.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_edit_note.*
import kotlinx.android.synthetic.main.layout_add_note.*
import java.text.SimpleDateFormat
import java.util.*

class EditNoteFragment: BaseFragment() {

    private lateinit var note: Note
    private lateinit var editNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_note, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //setHasOptionsMenu(true)

        editNoteViewModel = (activity as MainActivity).noteViewModel

        arguments?.let {
            note = EditNoteFragmentArgs.fromBundle(it).note
            etTitleNote.setText(note.title)
            etDescNote.setText(note.desc)
        }

        fabDoneNote.setOnClickListener {
            updateNote()
        }

        fabDeleteNote.setOnClickListener {
            deleteNote()
        }
    }

    private fun deleteNote() {
        if(note!=null){

            val deleteNoteAlertDialog = AlertDialog.Builder(requireActivity())
                    .setTitle("Delete Note")
                    .setMessage("Do you want to delete this note?")
                    .setPositiveButton("Yes") {_, _ ->
                        editNoteViewModel.deleteNote(note)
                        findNavController().navigate(R.id.action_editNoteFragment_to_showNoteFragment)
                    }
                    .setNegativeButton("No") {_, _ ->
                    }
                    .create()

            deleteNoteAlertDialog.show()
        }
    }

    private fun updateNote() {

        val titleNote =  etTitleNote.text.toString().trim()
        val descNote = etDescNote.text.toString().trim()

        val currentDate = SimpleDateFormat("dd MMMM yyyy").format(Date())
        val currentTime = SimpleDateFormat("HH:mm").format(Date())

        if (titleNote.isNotEmpty() && descNote.isNotEmpty() && currentDate.isNotEmpty() && currentTime.isNotEmpty()){
            val updNote = Note(note.uid,titleNote,descNote, currentDate, currentTime)
            updNote.noteId = note.noteId
            editNoteViewModel.updateNote(updNote)
            findNavController().navigate(R.id.action_editNoteFragment_to_showNoteFragment)
        }
        else{
            if (titleNote.isEmpty()) {
                etTitleNote.error = "Please Give Title"
                etTitleNote.requestFocus()
            }
            if(descNote.isEmpty()){
                etDescNote.error = "Please Write Description"
                etDescNote.requestFocus()
            }
        }
    }
}