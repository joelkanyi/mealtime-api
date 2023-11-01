package com.joelkanyi.mealtime.api.mealtimeapi.auth.controller

import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.AuthenticationResponse
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.LoginRequest
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.RegisterRequest
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.User
import com.joelkanyi.mealtime.api.mealtimeapi.auth.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("api/auth")
class AuthController(
    private val userService: UserService
) {
    @PostMapping("register")
    @ResponseStatus(HttpStatus.OK)
    fun register(
        @RequestBody registerRequest: RegisterRequest
    ): AuthenticationResponse {
        return userService.register(registerRequest)
    }

    @PostMapping("login")
    fun login(
        @RequestBody loginRequest: LoginRequest
    ): AuthenticationResponse {
        return userService.login(loginRequest)
    }

    @GetMapping("user")
    fun getUser(userId: String): User {
        return userService.getUser(userId)
    }
}