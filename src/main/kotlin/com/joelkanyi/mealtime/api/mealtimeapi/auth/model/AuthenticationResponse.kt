package com.joelkanyi.mealtime.api.mealtimeapi.auth.model

data class AuthenticationResponse(
    val token: String,
    val user: User?,
)
