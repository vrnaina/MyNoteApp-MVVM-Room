package com.vimalnaina.mynote.ui.fragment

import android.content.Context
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vimalnaina.mynote.R
import com.vimalnaina.mynote.adapter.NoteAdapter
import com.vimalnaina.mynote.ui.MainActivity
import com.vimalnaina.mynote.util.SharedPreference
import com.vimalnaina.mynote.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_show_note.*

class ShowNoteFragment : BaseFragment() {

    private lateinit var noteAdapter: NoteAdapter
    private lateinit var showNoteViewModel: NoteViewModel
    private lateinit var userPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = activity?.currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(
                    currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val closeAppAlertDialog = AlertDialog.Builder(activity!!)
                    .setTitle("Close App")
                    .setMessage("Do you want to exit application?")
                    .setPositiveButton("Yes") {_, _ ->
                        activity!!.finish()
                    }
                    .setNegativeButton("No") {_, _ ->
                    }
                    .create()

                closeAppAlertDialog.show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_note, container, false)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        showNoteViewModel = (activity as MainActivity).noteViewModel

        getNotes()

        fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_showNoteFragment_to_addNoteFragment)
        }
    }

    private fun getNotes() {
        userPreference = activity?.let { SharedPreference(it) }!!
        val uid = userPreference.getValueInt("userId")

        showNoteViewModel.getAllNotes(uid).observe(viewLifecycleOwner, {
            noteAdapter = NoteAdapter(it)
            rvNoteList.adapter = noteAdapter
            rvNoteList.layoutManager = LinearLayoutManager(activity)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miLogOut -> {logoutUser()}
            R.id.miDeleteAllNote -> {deleteAllNotes()}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        val deleteAllNoteAlertDialog = AlertDialog.Builder(requireActivity())
                .setTitle("Delete All Notes")
                .setMessage("Do you want to delete all notes?")
                .setPositiveButton("Yes") {_, _ ->
                    showNoteViewModel.deleteAllNotes()
                }
                .setNegativeButton("No") {_, _ ->
                }
                .create()

        deleteAllNoteAlertDialog.show()
    }

    private fun logoutUser() {
        val userPreference = activity?.let { SharedPreference(it) }
        if (userPreference != null) {
            if(userPreference.getValueString("userEmail")!=null){
                userPreference.clearSharedPreference()
                Toast.makeText(activity, "User Logged Out!!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_showNoteFragment_to_loginFragment)
            }
            else{
                Toast.makeText(activity, "User Not Logged In!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}