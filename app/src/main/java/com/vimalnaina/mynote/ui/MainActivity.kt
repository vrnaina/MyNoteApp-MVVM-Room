package com.vimalnaina.mynote.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.vimalnaina.mynote.R
import com.vimalnaina.mynote.db.NoteDatabase
import com.vimalnaina.mynote.repository.NoteRepository
import com.vimalnaina.mynote.repository.UserRepository
import com.vimalnaina.mynote.viewmodel.NoteViewModel
import com.vimalnaina.mynote.viewmodel.NoteViewModelProviderFactory
import com.vimalnaina.mynote.viewmodel.UserViewModel
import com.vimalnaina.mynote.viewmodel.UserViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var userViewModel : UserViewModel
    lateinit var noteViewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModels()

        setupActionBarWithNavController(findNavController(R.id.navHost))
    }

    private fun setUpViewModels() {
        val userRepository = UserRepository(NoteDatabase(this))
        val userViewModelProviderFactory = UserViewModelProviderFactory(userRepository)
        userViewModel = ViewModelProvider(this, userViewModelProviderFactory).get(UserViewModel::class.java)

        val noteRepository = NoteRepository(NoteDatabase(this))
        val noteViewModelProviderFactory = NoteViewModelProviderFactory(noteRepository)
        noteViewModel = ViewModelProvider(this, noteViewModelProviderFactory).get(NoteViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController =findNavController(R.id.navHost)
        val appBarConfiguration = AppBarConfiguration(navController.graph,null)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

}