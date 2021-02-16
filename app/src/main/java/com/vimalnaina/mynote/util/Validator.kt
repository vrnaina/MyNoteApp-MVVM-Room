package com.vimalnaina.mynote.util

import java.util.regex.*;

object Validator {

    fun validateEmail(email: String) : Boolean{
        val EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        val pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        if (matcher.matches()){
            return true
        }
        return false
    }

    fun validatePassword(password: String) : Boolean{
        val PASS_REGEX = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
        val pattern = Pattern.compile(PASS_REGEX)
        val matcher = pattern.matcher(password)
        if (matcher.matches()){
            return true
        }
        return false
    }

    fun validatePhone(phone: String) : Boolean{
        if (phone.length < 10){
            return false
        }
        return true
    }

    fun validateName(name: String) : Boolean{
        val pattern = Pattern.compile("[a-zA-Z ]+")
        val matcher = pattern.matcher(name)
        if (matcher.matches()){
            return true
        }
        return false
    }

}