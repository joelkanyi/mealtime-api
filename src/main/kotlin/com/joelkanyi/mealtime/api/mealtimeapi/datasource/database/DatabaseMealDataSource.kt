package com.joelkanyi.mealtime.api.mealtimeapi.datasource.database

import com.joelkanyi.mealtime.api.mealtimeapi.datasource.MealDataSource
import com.joelkanyi.mealtime.api.mealtimeapi.datasource.database.table.MealTable
import com.joelkanyi.mealtime.api.mealtimeapi.datasource.database.table.rowToMeal
import com.joelkanyi.mealtime.api.mealtimeapi.model.Meal
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository("database")
@Transactional
class DatabaseMealDataSource : MealDataSource {
    private val mealTable = MealTable
    override fun retrieveMeals(): List<Meal> {
        return mealTable.selectAll().map { it.toMeal() }
    }

    override fun retrieveMeal(mealId: String): Meal {
        return mealTable
            .select { mealTable.id eq mealId }
            .limit(1)
            .map { it.toMeal() }
            .firstOrNull() ?: throw NoSuchElementException("Could not find a meal with id $mealId")
    }

    override fun addMeal(meal: Meal): Meal {
        val meals = retrieveMeals()
        val newId = UUID.randomUUID().toString()
        if (meals.any { it.id == meal.id }) {
            throw IllegalArgumentException("Meal with id ${meal.id} already exists")
        }
        mealTable.insert {
            it[id] = newId
            it[name] = meal.name
            it[description] = meal.description
            it[price] = meal.price
            it[imageUrl] = meal.imageUrl
        }
        return retrieveMeal(newId)
    }

    override fun updateMeal(meal: Meal): Meal {
        val currentMeal = retrieveMeal(meal.id)
        if (currentMeal.id != meal.id) {
            throw NoSuchElementException("Could not find a meal with id ${meal.id}")
        } else {
            mealTable.update({ mealTable.id eq meal.id }) {
                it[name] = meal.name
                it[description] = meal.description
                it[price] = meal.price
                it[imageUrl] = meal.imageUrl
            }
            return retrieveMeal(meal.id)
        }
    }

    override fun deleteMeal(mealId: String) {
        val currentMeal = retrieveMeal(mealId)
        if (currentMeal.id != mealId) {
            throw NoSuchElementException("Could not find a meal with id $mealId")
        } else {
            mealTable.deleteWhere { mealTable.id eq mealId }
        }
    }

    private fun ResultRow.toMeal() = MealTable.rowToMeal(this)
}