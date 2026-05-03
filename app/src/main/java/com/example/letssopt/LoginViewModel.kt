package com.example.letssopt

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


sealed class LoginEvent {
    object LoginSuccess : LoginEvent()
    data class ShowToast(val message: String) : LoginEvent()
}

class LoginViewModel : ViewModel() {


    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password


    private var registeredEmail = ""
    private var registeredPassword = ""


    private val _loginEvent = MutableSharedFlow<LoginEvent>()
    val loginEvent = _loginEvent.asSharedFlow()


    fun updateEmail(input: String) { _email.value = input }
    fun updatePassword(input: String) { _password.value = input }


    fun setRegisteredData(email: String, pw: String) {
        registeredEmail = email
        registeredPassword = pw
    }


    fun login() {
        if (_email.value.isNotEmpty() && _email.value == registeredEmail && _password.value == registeredPassword) {
            viewModelScope.launch {
                _loginEvent.emit(LoginEvent.LoginSuccess)
            }
        } else {
            viewModelScope.launch {
                _loginEvent.emit(LoginEvent.ShowToast("이메일 또는 비밀번호가 일치하지 않습니다."))
            }
        }
    }
}