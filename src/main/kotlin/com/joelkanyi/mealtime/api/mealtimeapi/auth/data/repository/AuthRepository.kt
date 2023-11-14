package com.joelkanyi.mealtime.api.mealtimeapi.auth.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.*
import com.joelkanyi.mealtime.api.mealtimeapi.user.model.UserData

interface AuthRepository  {
    fun findByEmail(email: String): User?
    fun register(registerRequest: RegisterRequest): AuthenticationResponse
    fun login(loginRequest: LoginRequest): AuthenticationResponse
    fun refreshToken(token: String): AuthenticationResponse
}