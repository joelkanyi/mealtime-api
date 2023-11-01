package com.joelkanyi.mealtime.api.mealtimeapi.mealplan.service

import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.data.dto.MealPlanDto
import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.data.repository.MealPlanRepository
import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.model.MealPlan
import org.springframework.stereotype.Service

@Service
class MealPlanService(
    private val mealPlanRepository: MealPlanRepository
) {
    fun retrieveMealPlan(date: String): MealPlan? {
        return mealPlanRepository.retrieveMealPlan(date)
    }

    fun addMealPlan(mealPlan: MealPlanDto): String {
        return mealPlanRepository.addMealPlan(mealPlan)
    }

    fun updateMealPlan(mealPlanId: Int, mealPlan: MealPlan): String {
        return mealPlanRepository.updateMealPlan(mealPlanId, mealPlan)
    }

    fun deleteMealPlan(mealPlanId: Int): String {
        return mealPlanRepository.deleteMealPlan(mealPlanId)
    }
}