package com.vimalnaina.mynote.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.vimalnaina.mynote.R
import com.vimalnaina.mynote.model.Note
import com.vimalnaina.mynote.ui.fragment.ShowNoteFragmentDirections
import kotlinx.android.synthetic.main.item_notes.view.*

class NoteAdapter(
    private val notes : List<Note>
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.itemView.apply {
            tvTitleNote.text = currentNote.title
            tvDescNote.text = currentNote.desc
            tvDateNote.text = currentNote.date
            tvTimeNote.text = currentNote.time
        }
        holder.itemView.setOnClickListener {
            val action = ShowNoteFragmentDirections.actionShowNoteFragmentToEditNoteFragment(currentNote)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

}