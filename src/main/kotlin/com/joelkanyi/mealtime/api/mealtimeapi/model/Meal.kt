package com.joelkanyi.mealtime.api.mealtimeapi.model

data class Meal(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
)
