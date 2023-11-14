package com.joelkanyi.mealtime.api.mealtimeapi.auth.service

import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.LoginRequest
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.AuthenticationResponse
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.RegisterRequest
import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.repository.UserRepository
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.User
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun register(registerRequest: RegisterRequest): AuthenticationResponse {
        return userRepository.register(registerRequest)
    }

    fun login(loginRequest: LoginRequest): AuthenticationResponse {
        return userRepository.login(loginRequest)
    }

    fun getUser(userId: String): User {
        return userRepository.getUser(userId)
    }

    fun refreshToken(token: String): AuthenticationResponse {
        return userRepository.refreshToken(token)
    }
}