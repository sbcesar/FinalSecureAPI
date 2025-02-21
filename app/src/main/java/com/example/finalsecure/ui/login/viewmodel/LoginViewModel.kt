package com.example.finalsecure.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _isPasswordVisible = MutableLiveData(false)
    val isPasswordVisible: LiveData<Boolean> = _isPasswordVisible

    private val _loginEnabled = MutableLiveData(false)
    val loginEnabled: LiveData<Boolean> = _loginEnabled

    fun updateEmail(newEmail: String) {
        _username.value = newEmail
        validateLogin()
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
        validateLogin()
    }

    fun togglePasswordVisibility() {
        _isPasswordVisible.value = !(_isPasswordVisible.value ?: false)
    }

    private fun validateLogin() {
        val passwordValue = _password.value ?: ""
        _loginEnabled.value = isValidPassword(passwordValue)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 3

    fun delayed(action: () -> Unit) {
        viewModelScope.launch {
            delay(4000) // 4 segundos
            action()
        }
    }

}