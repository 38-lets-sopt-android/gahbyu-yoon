package com.example.letssopt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.dto.SignInRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class SignInUiState {
    object Idle : SignInUiState()
    object Loading : SignInUiState()
    object Success : SignInUiState()
    data class Error(val message: String) : SignInUiState()
}

class SignInViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<SignInUiState>(SignInUiState.Idle)
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()
    val loginId = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateLoginId(input: String) {
        loginId.value = input
    }

    fun updatePassword(input: String) {
        password.value = input
    }

    fun isSignInValid(): Boolean {
        return loginId.value.isNotBlank() && password.value.isNotBlank()
    }

    fun resetState() {
        _uiState.value = SignInUiState.Idle
    }


    fun signIn() = viewModelScope.launch {
        _uiState.value = SignInUiState.Loading

        runCatching {
            RetrofitClient.apiService.signIn(
                SignInRequest(loginId.value, password.value)
            )
        }.onSuccess { response ->
            if (response.isSuccessful) {
                _uiState.value = SignInUiState.Success
            } else {
                val message = response.body()?.message ?: "로그인에 실패했습니다"
                _uiState.value = SignInUiState.Error(message)
            }
        }.onFailure { e ->
            _uiState.value = SignInUiState.Error(e.message ?: "네트워크 오류가 발생했습니다")
        }
    }
}



