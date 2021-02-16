package com.vimalnaina.mynote.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(
    val uid : Int,
    val title : String,
    val desc : String,
    val date : String,
    val time: String
): Serializable{
    @PrimaryKey(autoGenerate = true)
    var noteId : Int = 0
}
