package com.example.letssopt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.dto.SignUpRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class SignUpUiState {
    object Idle : SignUpUiState()
    object Loading : SignUpUiState()
    object Success : SignUpUiState()
    data class Error(val message: String) : SignUpUiState()
}

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Idle)
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()
    val loginId = MutableStateFlow("")
    val password = MutableStateFlow("")
    val passwordCheck = MutableStateFlow("")
    val name = MutableStateFlow("")
    val email = MutableStateFlow("")
    val age = MutableStateFlow("")
    val part = MutableStateFlow("안드로이드")

    fun updateLoginId(input: String) {
        loginId.value = input
    }

    fun updateName(input: String) {
        name.value = input
    }

    fun updateEmail(input: String) {
        email.value = input
    }

    fun updateAge(input: String) {
        age.value = input.filter { it.isDigit() }
    }

    fun updatePart(input: String) {
        part.value = input
    }

    fun updatePassword(input: String) {
        if (input.length <= 12) password.value = input
    }

    fun updatePasswordCheck(input: String) {
        if (input.length <= 12) passwordCheck.value = input
    }

    fun isSignUpValid(): Boolean {
        return loginId.value.isNotBlank() &&
                password.value.isNotBlank() &&
                passwordCheck.value.isNotBlank() &&
                name.value.isNotBlank() &&
                email.value.isNotBlank() &&
                age.value.isNotBlank() &&
                password.value == passwordCheck.value
    }

    fun resetState() {
        _uiState.value = SignUpUiState.Idle
    }


    fun signUp() = viewModelScope.launch {
        _uiState.value = SignUpUiState.Loading

        runCatching {
            RetrofitClient.apiService.signUp(
                SignUpRequest(
                    loginId = loginId.value,
                    password = password.value,
                    name = name.value,
                    email = email.value,
                    age = age.value.toIntOrNull() ?: 0,
                    part = part.value
                )
            )
        }.onSuccess { response ->
            if (response.isSuccessful) {
                _uiState.value = SignUpUiState.Success
            } else {
                val message = response.body()?.message ?: "회원가입에 실패했습니다"
                _uiState.value = SignUpUiState.Error(message)
            }
        }.onFailure { e ->
            _uiState.value = SignUpUiState.Error(e.message ?: "네트워크 오류가 발생했습니다")
        }
    }
}