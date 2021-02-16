package com.vimalnaina.mynote.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vimalnaina.mynote.model.User
import com.vimalnaina.mynote.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun addUser(user : User) = viewModelScope.launch{
        userRepository.addUser(user)
    }

    fun getAllUsers() : LiveData<List<User>> {
        return userRepository.getAllUsers()
    }

    suspend fun getUserPass(userEmail : String) : String? {
        return userRepository.getUserPass(userEmail)
    }

    suspend fun getUserId(userEmail: String) : Int{
        return userRepository.getUserId(userEmail)
    }
}