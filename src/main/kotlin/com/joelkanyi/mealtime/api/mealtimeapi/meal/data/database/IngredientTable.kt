package com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database

import com.joelkanyi.mealtime.api.mealtimeapi.meal.model.Ingredient
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object IngredientTable : IntIdTable("ingredient") {
    val ingredient_name = varchar("ingredient_name", 255)
    val quantity = varchar("quantity", 255).nullable()
    val meal_id = varchar("meal_id", 255) references MealTable.meal_id
}

fun rowToIngredients(row: ResultRow) = Ingredient(
    id = row[IngredientTable.id].value,
    name = row[IngredientTable.ingredient_name],
    quantity = row[IngredientTable.quantity],
)