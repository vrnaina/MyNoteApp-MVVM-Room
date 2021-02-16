package com.vimalnaina.mynote.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vimalnaina.mynote.R
import com.vimalnaina.mynote.model.Note
import com.vimalnaina.mynote.ui.MainActivity
import com.vimalnaina.mynote.util.SharedPreference
import com.vimalnaina.mynote.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.layout_add_note.*
import java.text.SimpleDateFormat
import java.util.*

class AddNoteFragment: BaseFragment(){

    private lateinit var addNoteViewModel: NoteViewModel
    private lateinit var userPreference: SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addNoteViewModel = (activity as MainActivity).noteViewModel

        fabDoneNote.setOnClickListener {
            addNote()
        }
    }

    private fun addNote() {
        val titleNote = etTitleNote.text.toString().trim()
        val descNote = etDescNote.text.toString().trim()

        val currentDate = SimpleDateFormat("dd MMMM yyyy").format(Date())
        val currentTime = SimpleDateFormat("HH:mm").format(Date())

        userPreference = activity?.let { SharedPreference(it) }!!
        val uid = userPreference.getValueInt("userId")

        if (titleNote.isNotEmpty() && descNote.isNotEmpty() && currentDate.isNotEmpty() && currentTime.isNotEmpty()){
            val note = Note(uid,titleNote, descNote, currentDate, currentTime)
            addNoteViewModel.addNote(note)
            findNavController().navigate(R.id.action_addNoteFragment_to_showNoteFragment)
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