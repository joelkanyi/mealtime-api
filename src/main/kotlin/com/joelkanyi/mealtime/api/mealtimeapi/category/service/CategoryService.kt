package com.joelkanyi.mealtime.api.mealtimeapi.category.service

import com.joelkanyi.mealtime.api.mealtimeapi.category.data.dto.CreateCategoryDto
import com.joelkanyi.mealtime.api.mealtimeapi.category.data.repository.CategoryRepository
import com.joelkanyi.mealtime.api.mealtimeapi.category.model.Category
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun addCategory(category: CreateCategoryDto): String {
        return categoryRepository.createCategory(category)
    }

    fun getAllCategories(): List<Category> {
        return categoryRepository.getAllCategories()
    }

    fun getCategoryById(id: Int): Category? {
        return categoryRepository.getCategoryById(id)
    }

    fun deleteCategory(id: Int) {
        return categoryRepository.deleteCategory(id)
    }
}