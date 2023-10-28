package com.joelkanyi.mealtime.api.mealtimeapi.datasource.mock

import com.joelkanyi.mealtime.api.mealtimeapi.datasource.MealDataSource
import com.joelkanyi.mealtime.api.mealtimeapi.model.Meal
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.NoSuchElementException

@Repository
class MockMealDataSource : MealDataSource {
    val fakeMeals = mutableListOf(
        Meal(UUID.randomUUID().toString(), "Ugali", "test", 0.0, "test"),
        Meal(UUID.randomUUID().toString(), "Chapati", "test", 0.0, "test"),
        Meal(UUID.randomUUID().toString(), "Rice", "test", 0.0, "test"),
        Meal(UUID.randomUUID().toString(), "Pilau", "test", 0.0, "test"),
    )

    override fun retrieveMeals(): List<Meal> {
        return fakeMeals
    }

    override fun retrieveMeal(mealId: String): Meal {
        return fakeMeals.firstOrNull {
            it.id == mealId
        } ?: throw NoSuchElementException("Could not find a meal with id $mealId")
    }

    override fun addMeal(meal: Meal): Meal {
        if (fakeMeals.any { it.id == meal.id }) {
            throw IllegalArgumentException("Meal with id ${meal.id} already exists")
        }
        fakeMeals.add(meal)
        return meal
    }

    override fun updateMeal(meal: Meal): Meal {
        val currentMeal = fakeMeals.firstOrNull {
            it.id == meal.id
        } ?: throw NoSuchElementException("Could not find a meal with id ${meal.id}")

        fakeMeals.remove(currentMeal)
        fakeMeals.add(meal)
        return meal
    }

    override fun deleteMeal(mealId: String) {
        val currentMeal = fakeMeals.firstOrNull {
            it.id == mealId
        } ?: throw NoSuchElementException("Could not find a meal with id $mealId")

        fakeMeals.remove(currentMeal)
    }
}