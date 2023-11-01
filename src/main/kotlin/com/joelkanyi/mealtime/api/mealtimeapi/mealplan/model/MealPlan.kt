package com.joelkanyi.mealtime.api.mealtimeapi.mealplan.model

import com.joelkanyi.mealtime.api.mealtimeapi.meal.model.Meal

data class MealPlan(
    val mealTypeName: String,
    val meals: List<Meal>,
    val date: String,
    val id: String,
)
