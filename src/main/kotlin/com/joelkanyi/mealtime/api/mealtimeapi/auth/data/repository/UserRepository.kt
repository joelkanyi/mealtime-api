package com.joelkanyi.mealtime.api.mealtimeapi.auth.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.LoginRequest
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.AuthenticationResponse
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.RegisterRequest
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.User

interface UserRepository  {
    fun findByEmail(email: String): User?
    fun register(registerRequest: RegisterRequest): AuthenticationResponse
    fun login(loginRequest: LoginRequest): AuthenticationResponse
    fun getUser(userId: String): User
}