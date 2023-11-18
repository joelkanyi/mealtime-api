package com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.dto

data class CreateFavoriteDto(
    val mealId: String,
    val mealName: String,
    val mealImageUrl: String,
)
