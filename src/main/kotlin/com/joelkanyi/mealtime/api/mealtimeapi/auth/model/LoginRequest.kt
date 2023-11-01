package com.joelkanyi.mealtime.api.mealtimeapi.auth.model

data class LoginRequest(
    val email: String,
    val password: String,
)
