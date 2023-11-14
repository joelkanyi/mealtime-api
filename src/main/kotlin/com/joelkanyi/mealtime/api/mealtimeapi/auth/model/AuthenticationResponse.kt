package com.joelkanyi.mealtime.api.mealtimeapi.auth.model

import com.joelkanyi.mealtime.api.mealtimeapi.user.model.UserData

data class AuthenticationResponse(
    val token: String,
    val user: UserData?,
)
