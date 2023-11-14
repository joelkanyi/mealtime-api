package com.joelkanyi.mealtime.api.mealtimeapi.category.controller

import com.joelkanyi.mealtime.api.mealtimeapi.category.data.dto.CreateCategoryDto
import com.joelkanyi.mealtime.api.mealtimeapi.category.model.Category
import com.joelkanyi.mealtime.api.mealtimeapi.category.service.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
class CategoryController(
    private val categoryService: CategoryService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCategory(
        @RequestBody category: CreateCategoryDto
    ): String {
        return categoryService.addCategory(category)
    }

    @GetMapping
    fun getAllCategories(): List<Category> {
        return categoryService.getAllCategories()
    }

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Int): Category? {
        return categoryService.getCategoryById(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCategory(@PathVariable id: Int) {
        categoryService.deleteCategory(id)
    }
}