package com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.database

import com.joelkanyi.mealtime.api.mealtimeapi.favorite.model.Favorite
import com.joelkanyi.mealtime.api.mealtimeapi.meal.model.Meal
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object FavoritesTable : IntIdTable("favorites") {
    val meal_id = varchar("meal_id", 255)
    val user_id = varchar("user_id", 255)
}

fun rowToFavorite(
    row: ResultRow,
    meal: Meal,
) = Favorite(
    id = row[FavoritesTable.id].value,
    mealId = row[FavoritesTable.meal_id],
    mealName = meal.name,
    mealImageUrl = meal.image,
)