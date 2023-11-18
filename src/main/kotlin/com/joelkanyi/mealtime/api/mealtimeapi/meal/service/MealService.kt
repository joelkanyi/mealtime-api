package com.joelkanyi.mealtime.api.mealtimeapi.meal.service

import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.dto.CreateIngredientDto
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.dto.CreateMealDto
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.repository.MealRepository
import com.joelkanyi.mealtime.api.mealtimeapi.meal.model.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class MealService(
   private val mealRepository: MealRepository
) {
    fun retrieveMeals(): List<Meal> = mealRepository.retrieveMeals()
    fun retrieveMeal(mealId: String): MealDetails = mealRepository.retrieveMeal(mealId)
    fun addMeal(meal: CreateMealDto): String {
        return mealRepository.addMeal(meal)
    }

    fun deleteMeal(mealId: String) {
        mealRepository.deleteMeal(mealId)
    }

    fun searchMeals(category: String?, name: String?, ingredient: String?): List<Meal> {
        return mealRepository.searchMeals(category, name, ingredient)
    }

    fun getRandomMeal(): MealDetails {
        return mealRepository.getRandomMeal()
    }

    fun getIngredients(): List<Ingredient> {
        return mealRepository.getIngredients()
    }
}