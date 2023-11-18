package com.joelkanyi.mealtime.api.mealtimeapi.meal.controller

import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.dto.CreateIngredientDto
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.dto.CreateMealDto
import com.joelkanyi.mealtime.api.mealtimeapi.meal.model.*
import com.joelkanyi.mealtime.api.mealtimeapi.meal.service.MealService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/meals")
class MealController(
    private val mealService: MealService,
) {
    @GetMapping
    fun getMeals(): List<Meal> {
        return mealService.retrieveMeals()
    }

    @GetMapping("/{mealId}")
    fun getMeal(@PathVariable mealId: String): MealDetails? {
        return mealService.retrieveMeal(mealId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addMeal(@RequestBody meal: CreateMealDto): String {
        return mealService.addMeal(meal)
    }

    @DeleteMapping("/{mealId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMeal(@PathVariable mealId: String) {
        mealService.deleteMeal(mealId)
    }

    @GetMapping("/search")
    fun searchMeals(
        category: String?,
        name: String?,
        ingredient: String?,
    ): List<Meal> {
        return mealService.searchMeals(
            category,
            name,
            ingredient
        )
    }

    @GetMapping("/random")
    fun getRandomMeal(): MealDetails {
        return mealService.getRandomMeal()
    }

    @GetMapping("ingredients")
    fun getIngredients(): List<Ingredient> {
        return mealService.getIngredients()
    }
}