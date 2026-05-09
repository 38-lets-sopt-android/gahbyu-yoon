package com.example.letssopt

import com.example.letssopt.dto.SignInRequest
import com.example.letssopt.dto.SignInResponse
import com.example.letssopt.dto.SignUpRequest
import com.example.letssopt.dto.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/v1/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @POST("api/v1/auth/signin")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>
}