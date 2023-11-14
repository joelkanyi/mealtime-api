package com.joelkanyi.mealtime.api.mealtimeapi.user.service

import com.joelkanyi.mealtime.api.mealtimeapi.user.data.UserRepository
import com.joelkanyi.mealtime.api.mealtimeapi.user.model.UserData
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getUser(userId: String): UserData {
        return userRepository.getUser(userId)
    }
}