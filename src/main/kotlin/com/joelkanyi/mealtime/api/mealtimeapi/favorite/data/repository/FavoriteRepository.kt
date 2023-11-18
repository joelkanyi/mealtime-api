package com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.dto.CreateFavoriteDto
import com.joelkanyi.mealtime.api.mealtimeapi.favorite.model.Favorite

interface FavoriteRepository {
    fun retrieveFavorites(
        userId: String
    ): List<Favorite>

    fun addFavorite(
        favorite: CreateFavoriteDto,
    ): String

    fun deleteFavorite(
        mealId: String,
        userId: String
    )
}