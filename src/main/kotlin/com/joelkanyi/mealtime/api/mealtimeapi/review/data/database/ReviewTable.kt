package com.joelkanyi.mealtime.api.mealtimeapi.review.data.database

import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.UserTable
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.MealTable
import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.dto.UserDto
import com.joelkanyi.mealtime.api.mealtimeapi.review.model.Review
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object ReviewTable : IntIdTable("review") {
    val user_id = varchar("user_id", 255) references UserTable.id
    val meal_id = varchar("meal_id", 255) references MealTable.meal_id
    val rating = integer("rating")
    val comment = varchar("comment", 255)
}

fun rowToReview(
    row: ResultRow,
    user: UserDto,
) = Review(
    id = row[ReviewTable.id].value,
    rating = row[ReviewTable.rating],
    comment = row[ReviewTable.comment],
    user = user
)