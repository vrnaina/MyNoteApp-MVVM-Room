package com.vimalnaina.mynote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vimalnaina.mynote.repository.UserRepository

class UserViewModelProviderFactory(val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T
    }
}