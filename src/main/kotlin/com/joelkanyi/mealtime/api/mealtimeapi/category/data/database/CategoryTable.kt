package com.joelkanyi.mealtime.api.mealtimeapi.category.data.database

import com.joelkanyi.mealtime.api.mealtimeapi.category.model.Category
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.MealTable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object CategoryTable : IntIdTable("category") {
    val category_name = varchar("category_name", 255)
}

fun rowToCategory(row: ResultRow) =
    Category(
        id = row[CategoryTable.id].value,
        name = row[CategoryTable.category_name],
    )