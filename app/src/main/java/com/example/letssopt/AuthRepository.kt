package com.example.letssopt

class AuthRepository {
    private var registeredEmail = ""
    private var registeredPassword = ""

    fun signUp(email: String, pw: String): Boolean {
        registeredEmail = email
        registeredPassword = pw
        return true
    }

    fun login(email: String, pw: String): Boolean {
        return email.isNotEmpty() && email == registeredEmail && pw == registeredPassword
    }
}