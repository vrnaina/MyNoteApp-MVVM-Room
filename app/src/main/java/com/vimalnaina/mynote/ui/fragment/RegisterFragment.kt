package com.vimalnaina.mynote.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.vimalnaina.mynote.R
import com.vimalnaina.mynote.model.User
import com.vimalnaina.mynote.ui.MainActivity
import com.vimalnaina.mynote.util.Validator
import com.vimalnaina.mynote.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment() {

    lateinit var registerViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        registerViewModel = (activity as MainActivity).userViewModel

        btnRegister.setOnClickListener {
            val fname = etFname.text.toString().trim()
            val lname = etLname.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val password = etPass.text.toString().trim()

            val isFname = Validator.validateName(fname)
            val isLname = Validator.validateName(lname)
            val isEmail = Validator.validateEmail(email)
            val isPassword = Validator.validatePassword(password)
            val isPhone = Validator.validatePhone(phone)

            if (fname.isEmpty()){
                etFname.error = "Please Enter First Name"
                etFname.requestFocus()
                return@setOnClickListener
            }
            if(!isFname){
                etFname.error = "Enter only alphabets"
                etFname.requestFocus()
                return@setOnClickListener
            }
            if (lname.isEmpty()){
                etLname.error = "Please Enter Last Name"
                etLname.requestFocus()
                return@setOnClickListener
            }
            if(!isLname){
                etLname.error = "Enter only alphabets"
                etLname.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()){
                etEmail.error = "Please Enter Email"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if (!isEmail) {
                etEmail.error = "Enter Valid Email"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if (phone.isEmpty()){
                etPhone.error = "Please Enter Phone Number"
                etPhone.requestFocus()
                return@setOnClickListener
            }
            if (!isPhone) {
                etPhone.error = "Enter Valid Phone Number"
                etPhone.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                etPass.error = "Please Enter Password"
                etPass.requestFocus()
                return@setOnClickListener
            }
            if (!isPassword) {
                etPass.error = "It should contain 8-20 characters,\n"+
                        "At least 1 digit, At least 1 special character,\n"+
                        "At least 1 uppercase and lowercase character, \n"+
                        "Doesn’t contain any white space"
                etPass.requestFocus()
                return@setOnClickListener
            }

            launch {
                val user = User(fname, lname, email, phone, password)
                context?.let {

                    val userEmail = registerViewModel.getUserPass(email)//UserDatabase(it).getUserDao().getUserPass(email)

                    if (userEmail == null){
                        registerViewModel.addUser(user)
                        //UserDatabase(it).getUserDao().addUser(user)
                        Toast.makeText(it,"User Added...", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    }
                    else{
                        Toast.makeText(it,"User Already Exist...", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}