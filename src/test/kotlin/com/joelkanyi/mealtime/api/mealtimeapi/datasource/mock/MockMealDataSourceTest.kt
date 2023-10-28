package com.joelkanyi.mealtime.api.mealtimeapi.datasource.mock

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MockMealDataSourceTest {
    private val mockMealDataSource = MockMealDataSource()

    @Test
    fun `should provide a collection of meals`() {
        // When
        val meals = mockMealDataSource.retrieveMeals()

        // Then
        assertEquals(meals.size, 1)
    }

}