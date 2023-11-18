package com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.database.FavoritesTable
import com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.database.rowToFavorite
import com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.dto.CreateFavoriteDto
import com.joelkanyi.mealtime.api.mealtimeapi.favorite.model.Favorite
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.springframework.stereotype.Repository

@Repository
class FavoriteRepositoryImpl : FavoriteRepository {
    private val favoritesTable = FavoritesTable
    override fun retrieveFavorites(userId: String): List<Favorite> {
        return favoritesTable
            .select {
                favoritesTable.user_id eq userId
            }
            .map { rowToFavorite(it) }
    }

    override fun addFavorite(favorite: CreateFavoriteDto, userId: String): String {
        favoritesTable
            .insert {
                it[meal_id] = favorite.mealId
                it[meal_name] = favorite.mealName
                it[meal_image_url] = favorite.mealImageUrl
                it[user_id] = userId
            }

        return "Favorite added"
    }

    override fun deleteFavorite(favoriteId: Int, userId: String) {
        favoritesTable
            .deleteWhere {
                favoritesTable.id eq favoriteId and (favoritesTable.user_id eq userId)
            }
    }
}