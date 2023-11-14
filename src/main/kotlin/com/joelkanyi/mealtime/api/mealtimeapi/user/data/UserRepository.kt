package com.joelkanyi.mealtime.api.mealtimeapi.user.data

import com.joelkanyi.mealtime.api.mealtimeapi.user.model.UserData

interface UserRepository {
    fun getUser(userId: String): UserData
}