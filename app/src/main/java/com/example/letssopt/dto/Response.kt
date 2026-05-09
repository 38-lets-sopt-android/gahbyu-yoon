package com.example.letssopt.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: String
)

@Serializable
data class SignInData(
    @SerialName("userId")
    val userId: Int
)
@Serializable
data class SignInResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: SignInData
)