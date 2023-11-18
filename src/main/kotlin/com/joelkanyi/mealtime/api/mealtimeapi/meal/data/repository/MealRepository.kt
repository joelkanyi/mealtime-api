package com.joelkanyi.mealtime.api.mealtimeapi.meal.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.dto.CreateMealDto
import com.joelkanyi.mealtime.api.mealtimeapi.meal.model.*

interface MealRepository {
    fun retrieveMeals(): List<Meal>
    fun retrieveMeal(mealId: String): MealDetails
    fun addMeal(meal: CreateMealDto): String
    fun deleteMeal(mealId: String)
    fun searchMeals(category: String?, name: String?, ingredient: String?): List<Meal>
    fun getRandomMeal(): MealDetails
    fun getIngredients(): List<Ingredient>
}