package com.joelkanyi.mealtime.api.mealtimeapi.meal.controller

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
}