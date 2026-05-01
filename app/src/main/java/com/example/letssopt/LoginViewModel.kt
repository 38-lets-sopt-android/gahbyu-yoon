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

class LoginViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {


    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password


    private val _loginEvent = MutableSharedFlow<LoginEvent>()
    val loginEvent = _loginEvent.asSharedFlow()


    fun updateEmail(input: String) { _email.value = input }
    fun updatePassword(input: String) { _password.value = input }



    fun login() {

        val isSuccess = authRepository.login(_email.value, _password.value)
        if (isSuccess) {
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