package com.vimalnaina.mynote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val fname: String,
    val lname: String,
    val email: String,
    val phone: String,
    val password: String
){
    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0
}