package com.joelkanyi.mealtime.api.mealtimeapi.meal.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.UserTable
import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.rowToUserDao
import com.joelkanyi.mealtime.api.mealtimeapi.category.data.database.CategoryTable
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.CookingInstructionTable
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.rowToCookingInstruction
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.IngredientTable
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.rowToIngredients
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.MealTable
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.rowToMealDetailsDto
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database.rowToMealDto
import com.joelkanyi.mealtime.api.mealtimeapi.meal.data.dto.CreateMealDto
import com.joelkanyi.mealtime.api.mealtimeapi.meal.model.*
import com.joelkanyi.mealtime.api.mealtimeapi.review.data.database.ReviewTable
import com.joelkanyi.mealtime.api.mealtimeapi.review.data.database.rowToReview
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository("meal_database")
@Transactional
class MealRepositoryImpl : MealRepository {
    private val userTable = UserTable
    private val mealTable = MealTable
    private val ingredientTable = IngredientTable
    private val cookingInstructionTable = CookingInstructionTable
    private val categoryTable = CategoryTable
    private val reviewTable = ReviewTable
    val join =
        mealTable fullJoin ingredientTable fullJoin cookingInstructionTable fullJoin reviewTable

    override fun retrieveMeals(): List<Meal> {
        return mealTable
            .selectAll()
            .map(::rowToMealDto)
    }

    override fun retrieveMeal(mealId: String): MealDetails {
        return mealTable
            .select { mealTable.meal_id eq mealId }
            .map {
                rowToMealDetailsDto(
                    mealDetailsDtoRow = it,
                    reviewDtos = reviewTable
                        .select { reviewTable.meal_id eq mealId }
                        .map { it1 ->
                            rowToReview(
                                row = it1,
                                user = userTable
                                    .select { userTable.id eq it1[reviewTable.user_id] }
                                    .map(::rowToUserDao)
                                    .first()
                            )
                        },
                    ingredients = ingredientTable
                        .select { ingredientTable.meal_id eq mealId }
                        .map(::rowToIngredients),
                    cookingInstructions = cookingInstructionTable
                        .select { cookingInstructionTable.meal_id eq mealId }
                        .map(::rowToCookingInstruction),
                )
            }
            .first()
    }

    override fun addMeal(meal: CreateMealDto): String {
        val newId = UUID.randomUUID().toString()
        mealTable.insert {
            it[user_id] = meal.userId
            it[meal_id] = newId
            it[meal_name] = meal.name
            it[meal_image_url] = meal.imageUrl
            it[meal_description] = meal.description
            it[meal_cooking_time] = meal.cookingTime
            it[meal_serving] = meal.serving
            it[meal_cooking_difficulty] = meal.cookingDifficulty
            it[meal_calories] = meal.calories
            it[meal_recipe_price] = meal.recipePrice
            it[meal_youtube_video_url] = meal.youtubeUrl
            it[meal_category] = meal.category
        }

        meal.ingredients.forEach { ingredient ->
            ingredientTable.insert {
                it[ingredient_name] = ingredient.name
                it[meal_id] = newId
            }
        }

        meal.cookingInstructions.forEach { cookingInstruction ->
            cookingInstructionTable.insert {
                it[meal_id] = newId
                it[cooking_instruction] = cookingInstruction.instruction
            }
        }

        return "Meal added successfully"
    }

    override fun deleteMeal(mealId: String) {
        cookingInstructionTable.deleteWhere { cookingInstructionTable.meal_id eq mealId }
        ingredientTable.deleteWhere { ingredientTable.meal_id eq mealId }
        reviewTable.deleteWhere { reviewTable.meal_id eq mealId }
        mealTable.deleteWhere { mealTable.meal_id eq mealId }
    }

    override fun searchMeals(category: String?, name: String?, ingredient: String?): List<Meal> {
        return mealTable
            .select {
                mealTable.meal_name.like("%$name%") or
                        mealTable.meal_category.like("%$category%") or
                        ingredientTable.ingredient_name.like("%$ingredient%")
            }
            .map(::rowToMealDto)
    }
}