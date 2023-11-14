package com.joelkanyi.mealtime.api.mealtimeapi.category.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.category.data.dto.CreateCategoryDto
import com.joelkanyi.mealtime.api.mealtimeapi.category.model.Category

interface CategoryRepository {
    fun getAllCategories(): List<Category>
    fun getCategoryById(id: Int): Category?
    fun createCategory(category: CreateCategoryDto): String
    fun deleteCategory(id: Int)
}