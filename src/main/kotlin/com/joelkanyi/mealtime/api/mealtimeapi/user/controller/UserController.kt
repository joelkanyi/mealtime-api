package com.joelkanyi.mealtime.api.mealtimeapi.user.controller

import com.joelkanyi.mealtime.api.mealtimeapi.user.model.UserData
import com.joelkanyi.mealtime.api.mealtimeapi.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController("api/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun getUser(@PathVariable userId: String): UserData {
        return userService.getUser(userId)
    }
}