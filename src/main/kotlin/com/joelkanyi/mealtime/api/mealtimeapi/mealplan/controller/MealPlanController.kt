package com.joelkanyi.mealtime.api.mealtimeapi.mealplan.controller

import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.data.dto.MealPlanDto
import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.model.MealPlan
import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.service.MealPlanService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/mealplanner")
class MealPlanController(
    private val mealPlanService: MealPlanService
) {
    @GetMapping("/{date}")
    fun getMealPlan(@PathVariable date: String): MealPlan? {
        return mealPlanService.retrieveMealPlan(date)
    }

    @PostMapping
    fun addMealPlan(@RequestBody mealPlan: MealPlanDto): String {
        return mealPlanService.addMealPlan(mealPlan)
    }

    @PutMapping("/{mealPlanId}")
    fun updateMealPlan(@PathVariable mealPlanId: Int, @RequestBody mealPlan: MealPlan): String {
        return mealPlanService.updateMealPlan(mealPlanId, mealPlan)
    }

    @DeleteMapping("/{mealPlanId}")
    fun deleteMealPlan(@PathVariable mealPlanId: Int): String {
        return mealPlanService.deleteMealPlan(mealPlanId)
    }
}