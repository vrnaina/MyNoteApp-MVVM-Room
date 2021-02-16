package com.vimalnaina.mynote.repository

import androidx.lifecycle.LiveData
import com.vimalnaina.mynote.db.NoteDatabase
import com.vimalnaina.mynote.model.User

class UserRepository(val db : NoteDatabase) {

    suspend fun addUser(user : User){
        db.getUserDao().addUser(user)
    }

    fun getAllUsers() : LiveData<List<User>> {
        return db.getUserDao().getAllUsers()
    }

    suspend fun getUserPass(userEmail : String) : String? {
        return db.getUserDao().getUserPass(userEmail)
    }

    suspend fun getUserId(userEmail: String) : Int{
        return db.getUserDao().getUserId(userEmail)
    }
}