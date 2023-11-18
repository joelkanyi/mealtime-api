package com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.database.FavoritesTable
import com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.database.rowToFavorite
import com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.dto.CreateFavoriteDto
import com.joelkanyi.mealtime.api.mealtimeapi.favorite.model.Favorite
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.MealTable
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.rowToMeal
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class FavoriteRepositoryImpl : FavoriteRepository {
    private val favoritesTable = FavoritesTable
    private val mealsTable = MealTable
    override fun retrieveFavorites(userId: String): List<Favorite> {
        return favoritesTable
            .join(mealsTable, JoinType.INNER, favoritesTable.meal_id, mealsTable.meal_id)
            .select {
                favoritesTable.user_id eq userId
            }
            .map {
                rowToFavorite(
                    row = it,
                    meal = rowToMeal(it),
                )
            }
    }

    override fun addFavorite(favorite: CreateFavoriteDto): String {
        favoritesTable
            .insert {
                it[meal_id] = favorite.mealId
                it[user_id] = favorite.userId
            }

        return "Favorite added"
    }

    override fun deleteFavorite(mealId: String, userId: String) {
        favoritesTable
            .deleteWhere {
                favoritesTable.meal_id eq mealId and (favoritesTable.user_id eq userId)
            }
    }
}