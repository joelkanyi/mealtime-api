package com.joelkanyi.mealtime.api.mealtimeapi.utils

data class ApiError(
    val message: String,
    val status: Int,
    val error: String,
    val path: String,
    val timestamp: Long,
)