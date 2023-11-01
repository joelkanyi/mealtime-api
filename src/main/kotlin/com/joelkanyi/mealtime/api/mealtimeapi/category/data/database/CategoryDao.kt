package com.joelkanyi.mealtime.api.mealtimeapi.category.data.database

import com.joelkanyi.mealtime.api.mealtimeapi.category.model.Category

interface CategoryDao {
    fun getAllCategories(): List<Category>
    fun getCategoryById(id: Int): Category?
    fun createCategory(category: Category): Category
}