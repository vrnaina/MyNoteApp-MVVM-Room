package com.vimalnaina.mynote.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {
    private val PREF_NAME = "userpref"
    val sharedPreference: SharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)

    fun save(key:String, value: String){
        val editor:SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(key,value)
        editor!!.commit()
    }

    fun save(key:String, value: Int){
        val editor:SharedPreferences.Editor = sharedPreference.edit()
        editor.putInt(key,value)
        editor.commit()
    }

    fun getValueString(key: String): String?{
        return sharedPreference.getString(key,null)
    }

    fun getValueInt(key: String): Int{
        return sharedPreference.getInt(key,0)
    }

    fun clearSharedPreference(){
        val editor:SharedPreferences.Editor = sharedPreference.edit()
        editor.clear()
        editor.commit()
    }
}