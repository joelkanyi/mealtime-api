package com.joelkanyi.mealtime.api.mealtimeapi.mealplan.data.database

import com.joelkanyi.mealtime.api.mealtimeapi.meal.model.Meal
import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.model.MealPlan
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object MealPlanTable : Table("meal_plan") {
    val meal_plan_id = varchar("meal_plan_id", 255)
    val meal_type_name = varchar("meal_type_name", 255)
    val date = varchar("date", 255)
    override val primaryKey = PrimaryKey(meal_plan_id)
}

fun rowToMealPlan(
    row: ResultRow,
    meals: List<Meal>
) = MealPlan(
    id = row[MealPlanTable.meal_plan_id],
    mealTypeName = row[MealPlanTable.meal_type_name],
    date = row[MealPlanTable.date],
    meals = meals
)