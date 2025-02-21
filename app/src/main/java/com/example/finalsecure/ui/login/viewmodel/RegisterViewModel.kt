package com.example.finalsecure.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _passwordRepeat = MutableLiveData("")
    val passwordRepeat: LiveData<String> = _passwordRepeat

    private val _province = MutableLiveData("")
    val province: LiveData<String> = _province

    private val _municipality = MutableLiveData("")
    val municipality: LiveData<String> = _municipality

    private val _registerEnabled = MutableLiveData(false)
    val registerEnabled: LiveData<Boolean> = _registerEnabled

    fun updateUsername(newUsername: String) {
        _username.value = newUsername
        validateRegister()
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
        validateRegister()
    }

    fun updatePasswordRepeat(newPasswordRepeat: String) {
        _passwordRepeat.value = newPasswordRepeat
        validateRegister()
    }

    fun updateProvince(newProvince: String) {
        _province.value = newProvince
    }

    fun updateMunicipality(newMunicipality: String) {
        _municipality.value = newMunicipality
    }

    private fun validateRegister() {
        val passwordValue = _password.value ?: ""
        val passwordRepeatValue = _passwordRepeat.value ?: ""
        _registerEnabled.value = isValidPassword(passwordValue) && passwordValue == passwordRepeatValue
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6

    fun delayed(action: () -> Unit) {
        viewModelScope.launch {
            delay(4000) // 4 segundos
            action()
        }
    }
}
