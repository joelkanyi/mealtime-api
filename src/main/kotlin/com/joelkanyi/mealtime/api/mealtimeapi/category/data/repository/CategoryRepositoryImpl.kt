package com.joelkanyi.mealtime.api.mealtimeapi.category.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.category.data.database.CategoryTable
import com.joelkanyi.mealtime.api.mealtimeapi.category.data.database.rowToCategory
import com.joelkanyi.mealtime.api.mealtimeapi.category.data.dto.CreateCategoryDto
import com.joelkanyi.mealtime.api.mealtimeapi.category.model.Category
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class CategoryRepositoryImpl : CategoryRepository {

    private val categoryTable = CategoryTable

    override fun getAllCategories(): List<Category> {
        return categoryTable.selectAll()
            .map(::rowToCategory)
    }

    override fun getCategoryById(id: Int): Category? {
        return categoryTable.select {
            categoryTable.id eq id
        }
            .map(::rowToCategory)
            .first()
    }

    override fun createCategory(category: CreateCategoryDto): String {
        categoryTable.insert {
            it[category_name] = category.name
        }
        return "Category created successfully"
    }

    override fun deleteCategory(id: Int) {
        categoryTable.deleteWhere {
            categoryTable.id eq id
        }
    }
}