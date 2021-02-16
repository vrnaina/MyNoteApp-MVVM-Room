package com.vimalnaina.mynote.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vimalnaina.mynote.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM User ORDER BY uid DESC")
    fun getAllUsers() : LiveData<List<User>>

    @Query("SELECT password FROM User WHERE email = :userEmail")
    suspend fun getUserPass(userEmail: String) : String?

    @Query("SELECT uid FROM User WHERE email = :userEmail")
    suspend fun getUserId(userEmail: String) : Int
}