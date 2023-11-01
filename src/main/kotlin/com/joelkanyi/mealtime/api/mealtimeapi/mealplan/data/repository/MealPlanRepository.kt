package com.joelkanyi.mealtime.api.mealtimeapi.mealplan.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.data.dto.MealPlanDto
import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.model.MealPlan

interface MealPlanRepository {
    fun retrieveMealPlan(date: String): MealPlan?
    fun addMealPlan(mealPlan: MealPlanDto): String
    fun updateMealPlan(mealPlanId: Int, mealPlan: MealPlan): String
    fun deleteMealPlan(mealPlanId: Int): String
}