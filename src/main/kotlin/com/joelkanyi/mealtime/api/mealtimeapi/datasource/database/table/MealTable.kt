package com.joelkanyi.mealtime.api.mealtimeapi.datasource.database.table

import com.joelkanyi.mealtime.api.mealtimeapi.model.Meal
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object MealTable : Table("meal") {
    val id: Column<String> = varchar("id", 255)
    override val primaryKey = PrimaryKey(id, name = "meal_pk")
    val name: Column<String> = varchar("name", 255)
    val description: Column<String> = varchar("description", 255)
    val price: Column<Double> = double("price")
    val imageUrl: Column<String> = varchar("imageUrl", 255)
}

fun MealTable.rowToMeal(row: ResultRow) = Meal(
    id = row[id],
    name = row[name],
    description = row[description],
    price = row[price],
    imageUrl = row[imageUrl],
)