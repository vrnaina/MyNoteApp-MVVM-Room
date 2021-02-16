package com.vimalnaina.mynote.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vimalnaina.mynote.model.Note
import com.vimalnaina.mynote.model.User

@Database(
    entities = [User::class , Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getNoteDao() : NoteDao

    companion object{

        @Volatile
        private var instance : NoteDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "note_database"
        ).build()

    }
}