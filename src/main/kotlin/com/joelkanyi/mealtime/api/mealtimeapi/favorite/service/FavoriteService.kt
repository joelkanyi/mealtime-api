package com.joelkanyi.mealtime.api.mealtimeapi.favorite.service

import com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.dto.CreateFavoriteDto
import com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.repository.FavoriteRepository
import com.joelkanyi.mealtime.api.mealtimeapi.favorite.model.Favorite
import org.springframework.stereotype.Service

@Service
class FavoriteService(
    private val favoriteRepository: FavoriteRepository
) {
    fun retrieveFavorites(
        userId: String
    ): List<Favorite> = favoriteRepository.retrieveFavorites(
        userId = userId
    )

    fun addFavorite(
        favorite: CreateFavoriteDto,
    ): String {
        return favoriteRepository.addFavorite(
            favorite = favorite,
        )
    }

    fun deleteFavorite(
        mealId: String,
        userId: String
    ) {
        favoriteRepository.deleteFavorite(
            mealId = mealId,
            userId = userId
        )
    }
}