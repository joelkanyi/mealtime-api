package com.joelkanyi.mealtime.api.mealtimeapi.auth.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.*

interface UserRepository  {
    fun findByEmail(email: String): User?
    fun register(registerRequest: RegisterRequest): AuthenticationResponse
    fun login(loginRequest: LoginRequest): AuthenticationResponse
    fun getUser(userId: String): UserData
    fun refreshToken(token: String): AuthenticationResponse
}