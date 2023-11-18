package com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.database

import com.joelkanyi.mealtime.api.mealtimeapi.favorite.model.Favorite
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object FavoritesTable : IntIdTable("favorite") {
    val meal_id = varchar("meal_id", 255)
    val meal_name = varchar("meal_name", 255)
    val meal_image_url = varchar("meal_image_url", 255)
    val user_id = varchar("user_id", 255)
}

fun rowToFavorite(
    row: ResultRow,
) = Favorite(
    id = row[FavoritesTable.id].value,
    mealId = row[FavoritesTable.meal_id],
    mealName = row[FavoritesTable.meal_name],
    mealImageUrl = row[FavoritesTable.meal_image_url],
)