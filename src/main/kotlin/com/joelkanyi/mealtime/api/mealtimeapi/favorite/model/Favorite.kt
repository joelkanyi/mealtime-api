package com.joelkanyi.mealtime.api.mealtimeapi.favorite.model

data class Favorite(
    val id: Int,
    val mealId: String,
    val mealName: String,
    val mealImageUrl: String,
)
