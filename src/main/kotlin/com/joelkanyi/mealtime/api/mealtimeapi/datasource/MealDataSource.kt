package com.joelkanyi.mealtime.api.mealtimeapi.datasource

import com.joelkanyi.mealtime.api.mealtimeapi.model.Meal

interface MealDataSource {
    fun retrieveMeals(): List<Meal>
    fun retrieveMeal(mealId: String): Meal
    fun addMeal(meal: Meal): Meal
    fun updateMeal(meal: Meal): Meal
    fun deleteMeal(mealId: String)
}