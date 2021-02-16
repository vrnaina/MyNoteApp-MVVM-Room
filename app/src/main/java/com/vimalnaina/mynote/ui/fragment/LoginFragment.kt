package com.vimalnaina.mynote.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.vimalnaina.mynote.R
import com.vimalnaina.mynote.ui.MainActivity
import com.vimalnaina.mynote.util.SharedPreference
import com.vimalnaina.mynote.util.Validator
import com.vimalnaina.mynote.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment(){

    private lateinit var loginViewModel: UserViewModel
    private lateinit var userPreference: SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loginViewModel = (activity as MainActivity).userViewModel

        userPreference = activity?.let { SharedPreference(it) }!!
        if(userPreference.getValueString("userEmail")==null){
            loginUser()
        }
        else{
            findNavController().navigate(R.id.action_loginFragment_to_showNoteFragment)
        }

        tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun loginUser() {

        btnLogin.setOnClickListener {

            val email = etLoginEmail.text.toString().trim()
            val password = etLoginPass.text.toString().trim()

            val isEmail : Boolean = Validator.validateEmail(email)
            val isPassword : Boolean = Validator.validatePassword(password)

            if (email.isEmpty()){
                etLoginEmail.error = "Please Enter Email"
                etLoginEmail.requestFocus()
                return@setOnClickListener
            }
            if (!isEmail) {
                etLoginEmail.error = "Enter Valid Email"
                etLoginEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                etLoginPass.error = "Please Enter Password"
                etLoginPass.requestFocus()
                return@setOnClickListener
            }
            if (!isPassword) {
                etLoginPass.error = "It should contain 8-20 characters,\n"+
                        "At least 1 digit, At least 1 special character,\n"+
                        "At least 1 uppercase and lowercase character, \n"+
                        "Doesn’t contain any white space"
                etLoginPass.requestFocus()
                return@setOnClickListener
            }

            launch {
                context?.let {
                    val userPass = loginViewModel.getUserPass(email)
                    val userId:Int? = loginViewModel.getUserId(email)

                    if (userPass == password){
                        userPreference.save("userEmail",email)
                        if(userId!=null)  userPreference.save("userId", userId)
                        Toast.makeText(activity, "User Logged In...", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_loginFragment_to_showNoteFragment)
                    }
                    else{
                        Toast.makeText(activity, "Wrong Email or Password...", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}