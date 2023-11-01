package com.joelkanyi.mealtime.api.mealtimeapi.mealplan.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.MealTable
import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.data.database.MealInPlanTable
import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.data.database.MealPlanTable
import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.data.database.rowToMealPlan
import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.data.dto.MealPlanDto
import com.joelkanyi.mealtime.api.mealtimeapi.mealplan.model.MealPlan
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Repository
@Transactional
class MealPlanRepositoryImpl : MealPlanRepository {
    private val mealPlaTable = MealPlanTable
    private val mealInPlanTable = MealInPlanTable

    override fun retrieveMealPlan(date: String): MealPlan? {
        TODO()
    }

    override fun addMealPlan(mealPlan: MealPlanDto): String {
        val newMealPlanId = UUID.randomUUID().toString()
        mealPlaTable.insert {
            it[meal_type_name] = mealPlan.mealTypeName
            it[date] = mealPlan.date
            it[meal_plan_id] = newMealPlanId
        }

        mealInPlanTable.insert {
            it[meal_id] = mealPlan.mealId
            it[meal_plan_id] = newMealPlanId
        }
        return "Meal Plan Added Successfully"
    }

    override fun updateMealPlan(mealPlanId: Int, mealPlan: MealPlan): String {
        return "Meal Plan Updated Successfully"
    }

    override fun deleteMealPlan(mealPlanId: Int): String {
        TODO("Not yet implemented")
    }
}