package com.joelkanyi.mealtime.api.mealtimeapi.meal.data.database

import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.UserTable
import com.joelkanyi.mealtime.api.mealtimeapi.meal.model.*
import com.joelkanyi.mealtime.api.mealtimeapi.review.model.Review
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object MealTable : Table("meal") {
    val user_id: Column<String> = varchar("user_id", 255) references UserTable.id
    val meal_id: Column<String> = varchar("meal_id", 255)
    val meal_name: Column<String> = varchar("meal_name", 255)
    val meal_description: Column<String?> = varchar("meal_description", 255).nullable()
    val meal_category: Column<String> = varchar("meal_category", 255)
    val meal_cooking_time: Column<Int?> = integer("meal_cooking_time").nullable()
    val meal_serving: Column<Int?> = integer("meal_serving").nullable()
    val meal_cooking_difficulty: Column<String?> = varchar("meal_cooking_difficulty", 255).nullable()
    val meal_calories: Column<Double?> = double("meal_calories").nullable()
    val meal_image_url: Column<String> = varchar("meal_image_url", 255)
    val meal_recipe_price: Column<Double?> = double("meal_recipe_price").nullable()
    val meal_youtube_video_url: Column<String?> = varchar("meal_youtube_video_url", 255).nullable()
    override val primaryKey = PrimaryKey(meal_id, name = "meal_pk")
}

fun rowToMeal(row: ResultRow) = Meal(
    id = row[MealTable.meal_id],
    name = row[MealTable.meal_name],
    image = row[MealTable.meal_image_url],
    category = row[MealTable.meal_category]
)

fun rowToMealDetails(
    mealDetailsDtoRow: ResultRow,
    reviewDtos: List<Review>,
    ingredients: List<Ingredient>,
    cookingInstructions: List<CookingInstruction>,
) = MealDetails(
    id = mealDetailsDtoRow[MealTable.meal_id],
    name = mealDetailsDtoRow[MealTable.meal_name],
    description = mealDetailsDtoRow[MealTable.meal_description],
    cookingTime = mealDetailsDtoRow[MealTable.meal_cooking_time],
    serving = mealDetailsDtoRow[MealTable.meal_serving],
    cookingDifficulty = mealDetailsDtoRow[MealTable.meal_cooking_difficulty],
    calories = mealDetailsDtoRow[MealTable.meal_calories],
    recipePrice = mealDetailsDtoRow[MealTable.meal_recipe_price]?.toDouble(),
    imageUrl = mealDetailsDtoRow[MealTable.meal_image_url],
    youtubeUrl = mealDetailsDtoRow[MealTable.meal_youtube_video_url],
    reviewDtos = reviewDtos,
    category = mealDetailsDtoRow[MealTable.meal_category],
    ingredients = ingredients,
    cookingInstructions = cookingInstructions,
)


// rowToMe


/*
Certainly, here are all the tables for your meal planning app, including the "User" table:

**User:**
- user_id (Primary Key)
- username
- email
- password (hashed and salted)
- profile picture
- other user-related information

**Meals:**
- meal_id (Primary Key)
- meal_name
- description
- ingredients
- category_id (Foreign Key referencing Categories)
- time_of_day
- is_favorite (Boolean to indicate if it's favorited)
- allergen_ids (Array of Foreign Keys referencing Allergies)
- reviewDtos (Array of Foreign Keys referencing Reviews)
- cooking_time
- serving
- cooking_difficulty
- calories

**Categories:**
- category_id (Primary Key)
- category_name

**Allergies:**
- allergy_id (Primary Key)
- allergy_name

**Ingredients:**
- ingredient_id (Primary Key)
- ingredient_name

**Meal Plans:**
- meal_plan_id (Primary Key)
- meal_plan_name
- meal_ids (Array of Foreign Keys referencing Meals)
- user_id (Foreign Key referencing Users)

**Reviews:**
- review_id (Primary Key)
- meal_id (Foreign Key referencing Meals)
- user_id (Foreign Key referencing Users)
- rating
- review_text

**Cooking Instructions:**
- instruction_id (Primary Key)
- meal_id (Foreign Key referencing Meals)
- step_number
- instruction_text

**Shopping Lists:**
- shopping_list_id (Primary Key)
- user_id (Foreign Key referencing Users)
- list_name
- item_ids (Array of Foreign Keys referencing Available Ingredients)

**Available Ingredients:**
- available_ingredient_id (Primary Key)
- ingredient_id (Foreign Key referencing Ingredients)
- quantity
- unit

**Favorites:**
- favorite_id (Primary Key)
- user_id (Foreign Key referencing Users)
- meal_id (Foreign Key referencing Meals)

These tables, along with their respective fields, provide a comprehensive structure to support the features you've listed in your meal planning app. You can expand or modify these tables as needed to meet specific requirements and data structures.
 */

/** User Table
 * user_id (Primary Key)
 * first_name
 * last_name
 * email
 * password (hashed and salted)
 * profile picture (url)
 */

/** Meal Table
 * meal_id (Primary Key)
 * meal_name
 * description
 * ingredients
 * cooking_instructions
 * category_id (Foreign Key referencing Categories)
 * time_of_day
 * is_favorite (Boolean to indicate if it's favorited)
 * allergen_ids (Array of Foreign Keys referencing Allergies)
 * reviewDtos (Array of Foreign Keys referencing Reviews)
 * cooking_time
 * serving
 * cooking_difficulty
 * calories
 * image_url
 * price
 * youtube_video_url
 */

/** Categories Table
 * category_id (Primary Key)
 * category_name
 * category_image_url
 */


/** Allergies Table
 * allergy_id (Primary Key)
 * allergy_name
 */

/** Ingredients Table
 * ingredient_id (Primary Key)
 * ingredient_name
 * ingredient_image_url
 */

/** Meal Plans Table
 * meal_plan_id (Primary Key)
 * meal_plan_name
 * meal_ids (Array of Foreign Keys referencing Meals)
 */

/** Reviews Table
 * review_id (Primary Key)
 * meal_id (Foreign Key referencing Meals)
 * user_id (Foreign Key referencing Users)
 * rating
 * review_text
 */

/** Cooking Instructions Table
 * instruction_id (Primary Key)
 * meal_id (Foreign Key referencing Meals)
 * step_number
 * instruction_text
 * instruction_image_url
 */

/** Shopping Lists Table
 * shopping_list_id (Primary Key)
 * user_id (Foreign Key referencing Users)
 * list_name
 * item_ids (Array of Foreign Keys referencing Available Ingredients)
 */

/** Available Ingredients Table
 * available_ingredient_id (Primary Key)
 * ingredient_id (Foreign Key referencing Ingredients)
 * quantity
 * unit
 */

/** Favorites Table
 * favorite_id (Primary Key)
 * user_id (Foreign Key referencing Users)
 * meal_id (Foreign Key referencing Meals)
 */

