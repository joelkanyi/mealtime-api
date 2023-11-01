package com.joelkanyi.mealtime.api.mealtimeapi.mealplan.data.database

import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.MealTable
import org.jetbrains.exposed.dao.id.IntIdTable

object MealInPlanTable : IntIdTable("meal_in_plan") {
    val meal_plan_id = varchar("meal_plan_id", 255) references MealPlanTable.meal_plan_id
    val meal_id = varchar("meal_id", 255) references MealTable.meal_id
    val meal_type_name = varchar("meal_type_name", 255)
}