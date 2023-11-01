package com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database

import com.joelkanyi.mealtime.api.mealtimeapi.meal.model.CookingInstruction
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object CookingInstructionTable : IntIdTable("cooking_instruction") {
    val meal_id = varchar("meal_id", 255) references MealTable.meal_id
    val cooking_instruction = varchar("cooking_instruction", 255)
}

fun rowToCookingInstruction(row: ResultRow) = CookingInstruction(
    id = row[CookingInstructionTable.id].value,
    instruction = row[CookingInstructionTable.cooking_instruction],
)