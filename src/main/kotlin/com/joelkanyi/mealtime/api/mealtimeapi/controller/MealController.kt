package com.joelkanyi.mealtime.api.mealtimeapi.controller

import com.joelkanyi.mealtime.api.mealtimeapi.model.Meal
import com.joelkanyi.mealtime.api.mealtimeapi.service.MealService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException
import java.util.UUID

@RestController
@RequestMapping("/mealtime/api")
class MealController(
    private val mealService: MealService
) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/meals")
    fun getMeals() : Collection<Meal> {
        return mealService.retrieveMeals()
    }

    @GetMapping("/meals/{mealId}")
    fun getMeal(@PathVariable mealId: String) : Meal? {
        return mealService.retrieveMeal(mealId)
    }

    @PostMapping("/meals")
    @ResponseStatus(HttpStatus.CREATED)
    fun addMeal(@RequestBody meal: Meal) : Meal {
        return mealService.addMeal(meal)
    }

    @PatchMapping("/meals")
    fun updateMeal(@RequestBody meal: Meal) : Meal {
        return mealService.updateMeal(meal)
    }

    @DeleteMapping("/meals/{mealId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMeal(@PathVariable mealId: String) {
        mealService.deleteMeal(mealId)
    }
}