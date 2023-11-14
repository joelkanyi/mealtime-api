package com.joelkanyi.mealtime.api.mealtimeapi.auth.service

import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.repository.AuthRepository
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.*
import com.joelkanyi.mealtime.api.mealtimeapi.user.model.UserData
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authRepository: AuthRepository
) {
    fun register(registerRequest: RegisterRequest): AuthenticationResponse {
        return authRepository.register(registerRequest)
    }

    fun login(loginRequest: LoginRequest): AuthenticationResponse {
        return authRepository.login(loginRequest)
    }

    fun refreshToken(token: String): AuthenticationResponse {
        return authRepository.refreshToken(token)
    }
}