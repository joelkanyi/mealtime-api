package com.joelkanyi.mealtime.api.mealtimeapi.service

import com.joelkanyi.mealtime.api.mealtimeapi.datasource.MealDataSource
import com.joelkanyi.mealtime.api.mealtimeapi.model.Meal
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MealService(
    @Qualifier("database") private val mealDataSource: MealDataSource
) {
    fun retrieveMeals(): List<Meal> = mealDataSource.retrieveMeals()
    fun retrieveMeal(mealId: String): Meal = mealDataSource.retrieveMeal(mealId)
    fun addMeal(meal: Meal): Meal {
        return mealDataSource.addMeal(meal)
    }

    fun updateMeal(meal: Meal): Meal {
        return mealDataSource.updateMeal(meal)
    }

    fun deleteMeal(mealId: String) {
        mealDataSource.deleteMeal(mealId)
    }
}