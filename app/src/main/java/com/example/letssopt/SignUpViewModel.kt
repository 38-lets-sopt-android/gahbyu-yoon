package com.example.letssopt

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


sealed class SignUpEvent {
    data class SignUpSuccess(val email: String, val password: String) : SignUpEvent()
    data class ShowToast(val message: String) : SignUpEvent()
}

class SignUpViewModel : ViewModel() {


    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _passwordCheck = mutableStateOf("")
    val passwordCheck: State<String> = _passwordCheck


    private val _signUpEvent = MutableSharedFlow<SignUpEvent>()
    val signUpEvent = _signUpEvent.asSharedFlow()


    fun updateEmail(input: String) {
        _email.value = input
    }

    fun updatePassword(input: String) {
        if (input.length <= 12) {
            _password.value = input
        }
    }

    fun updatePasswordCheck(input: String) {
        if (input.length <= 12) {
            _passwordCheck.value = input
        }
    }


    fun isSignUpValid(): Boolean {
        return _email.value.isNotBlank() &&
                _password.value.isNotBlank() &&
                _passwordCheck.value.isNotBlank() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value).matches() &&
                _password.value.length >= 8 &&
                _passwordCheck.value.length >= 8
    }


    fun signUp() {
        if (_password.value == _passwordCheck.value) {

            viewModelScope.launch {
                _signUpEvent.emit(
                    SignUpEvent.SignUpSuccess(
                        _email.value,
                        _password.value
                    )
                )
            }
        } else {

            viewModelScope.launch {
                _signUpEvent.emit(SignUpEvent.ShowToast("비밀번호가 일치하지 않습니다."))
            }
        }
    }
}