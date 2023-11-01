package com.joelkanyi.mealtime.api.mealtimeapi.category.controller

import com.joelkanyi.mealtime.api.mealtimeapi.category.data.dto.CreateCategoryDto
import com.joelkanyi.mealtime.api.mealtimeapi.category.model.Category
import com.joelkanyi.mealtime.api.mealtimeapi.category.service.CategoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/categories")
class CategoryController(
    private val categoryService: CategoryService
) {
    @PostMapping
    fun addCategory(category: CreateCategoryDto) {
        categoryService.addCategory(category)
    }

    @GetMapping
    fun getAllCategories(): List<Category> {
        return categoryService.getAllCategories()
    }

    @GetMapping("/{id}")
    fun getCategoryById(id: Int): Category? {
        return categoryService.getCategoryById(id)
    }
}