package com.joelkanyi.mealtime.api.mealtimeapi.meal.model

import com.joelkanyi.mealtime.api.mealtimeapi.review.model.Review

data class MealDetails(
    val id: String,
    val name: String,
    val description: String?,
    val recipePrice: Double?,
    val imageUrl: String,
    val ingredients: List<Ingredient>,
    val cookingInstructions: List<CookingInstruction>,
    val reviewDtos: List<Review>,
    val youtubeUrl: String?,
    val cookingTime: Int?,
    val serving: Int?,
    val cookingDifficulty: String?,
    val calories: Double?,
    val category: String,
)
