package com.joelkanyi.mealtime.api.mealtimeapi.auth.controller

import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.*
import com.joelkanyi.mealtime.api.mealtimeapi.auth.service.AuthService
import com.joelkanyi.mealtime.api.mealtimeapi.user.model.UserData
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("register")
    @ResponseStatus(HttpStatus.OK)
    fun register(
        @RequestBody registerRequest: RegisterRequest
    ): AuthenticationResponse {
        return authService.register(registerRequest)
    }

    @PostMapping("login")
    fun login(
        @RequestBody loginRequest: LoginRequest
    ): AuthenticationResponse {
        return authService.login(loginRequest)
    }

    @PostMapping("refresh")
    fun refreshToken(
        @RequestBody refreshTokenRequest: RefreshTokenRequest
    ):AuthenticationResponse {
        return authService.refreshToken(refreshTokenRequest.token)
    }
}