package com.joelkanyi.mealtime.api.mealtimeapi.review.model

import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.dto.UserDto

data class Review(
    val id: Int,
    val rating: Int,
    val comment: String,
    val user: UserDto,
)
