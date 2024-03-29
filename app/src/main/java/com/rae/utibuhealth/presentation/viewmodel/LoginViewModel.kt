package com.rae.utibuhealth.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.rae.utibuhealth.data.model.LoginRequest
import com.rae.utibuhealth.data.model.RegisterRequest
import com.rae.utibuhealth.repository.MainRepository

class LoginSignupViewModel(private val repository: MainRepository):ViewModel() {
  //login data
    var email by mutableStateOf("")
      private set
    var password by mutableStateOf("")
        private set
    var checked by mutableStateOf(false)
        private set

    //signup data
    var firstName by mutableStateOf("")
        private set
    var lastName by mutableStateOf("")
        private set
    var emailReg by mutableStateOf("")
        private set
    var passwordReg by mutableStateOf("")
        private set
    //the events
    var confrimpass by mutableStateOf("")
   fun onEmailRegChange(newRegEmail: String){
       emailReg = newRegEmail
   }
    fun onPasswordRegChange(newRegPassword: String){
        passwordReg = newRegPassword
    }
    fun onCheckedChange(isChecked: Boolean){
        checked = isChecked
    }
    //register events
    fun onFirstName(newFirstName: String){
        firstName = newFirstName
    }
    fun onLastName(newLastName: String){
        lastName = newLastName
    }
    fun onEmailChange(newEmail: String){
        email = newEmail
    }
    fun onPasswordChange(newPassword: String){
        password = newPassword
    }
    fun onConfirmPassword(confirmPassword: String){
        confrimpass = confirmPassword
        isRegisterEnabled()
    }
    private fun confirmPasswordMatches(): Boolean {
        return passwordReg == confrimpass
    }

    fun isEnabledLog(): Boolean{
        return email.isNotEmpty()&&password.isNotEmpty()
    }
    fun isRegisterEnabled(): Boolean{
        return emailReg.isNotEmpty()&&passwordReg.isNotEmpty()
    }
    //signupRequest
  suspend  fun signup(){
        if (isRegisterEnabled()){
            val registerRequest = RegisterRequest(
                email = emailReg,
                firstName = firstName,
                lastName = lastName,
                password = passwordReg
            )
            val response = repository.signup(registerRequest)
        }
    }
    suspend  fun login(){
        if (isEnabledLog()){
            val loginRequest = LoginRequest(
                email = email,
                password = password
            )
            val response = repository.login(loginRequest)
        }
    }


}