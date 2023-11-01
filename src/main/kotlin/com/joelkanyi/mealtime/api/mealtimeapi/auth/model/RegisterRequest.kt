package com.joelkanyi.mealtime.api.mealtimeapi.auth.model

data class RegisterRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val profilePictureUrl: String,
)
