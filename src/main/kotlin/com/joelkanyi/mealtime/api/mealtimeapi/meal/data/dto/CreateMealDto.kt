package com.joelkanyi.mealtime.api.mealtimeapi.meal.data.dto

data class CreateMealDto(
    val userId: String,
    val name: String,
    val description: String?,
    val recipePrice: Double?,
    val imageUrl: String,
    val ingredients: List<CreateIngredientDto>,
    val cookingInstructions: List<CookingInstructionDto>,
    val youtubeUrl: String?,
    val cookingTime: Int?,
    val serving: Int?,
    val cookingDifficulty: String?,
    val calories: Double?,
    val category: String,
)
