/*
package com.joelkanyi.mealtime.api.mealtimeapi.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.joelkanyi.mealtime.api.mealtimeapi.meal.model.Meal
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
class MealControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val baseUrl = "/mealtime/api"

    @Test
    fun `should return a collection of meals`() {
        // when/then
        mockMvc.get("$baseUrl/meals")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
    }

    @Test
    fun `should return a meal with the given id`() {
        // given
        val mealId = "0"

        // when/then
        mockMvc.get("$baseUrl/meals/$mealId")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name") { value("Ugali") }
            }
    }

    @Test
    fun `return NOT FOUND if the meal does not exist`() {
        // given
        val mealId = "tree"

        // when/then
        mockMvc.get("$baseUrl/meals/$mealId")
            .andDo { print() }
            .andExpect {
                status { isNotFound() }
            }
    }

    @Test
    fun `should a new meal`() {
        // given
        val newMeal = Meal(
            "Chips",
            "Chips",
            "test",
            0.0,
            "test"
        )

        // when
        val performPost = mockMvc.post("$baseUrl/meals") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newMeal)
        }

        // then
        performPost.andDo { print() }
            .andExpect {
                status { isCreated() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name") { value("Chips") }
            }
    }

    @Test
    fun `should return BAD REQUEST if meal with the same id exist`() {
        // given
        val newMeal = Meal(
            "Ugali",
            "Chips",
            "test",
            0.0,
            "test"
        )

        // when
        val performPost = mockMvc.post("$baseUrl/meals") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newMeal)
        }

        // then
        performPost.andDo { print() }
            .andExpect {
                status { isBadRequest() }
            }
    }

    @Test
    fun `should update an existing meal`() {
        // given
        val updatedMeal = Meal(
            "Chips",
            "Chips",
            "test",
            0.0,
            "test"
        )

        // when
        val performPut = mockMvc.patch("$baseUrl/meals") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(updatedMeal)
        }

        // then
        performPut.andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name") { value("Chips") }
            }
    }

    @Test
    fun `should return NOT FOUND if meal to be updated does not exist`() {
        // given
        val updatedMeal = Meal(
            "tree",
            "Chips",
            "test",
            0.0,
            "test"
        )

        // when
        val performPut = mockMvc.patch("$baseUrl/meals") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(updatedMeal)
        }

        // then
        performPut.andDo { print() }
            .andExpect {
                status { isNotFound() }
            }
    }

    @Test
    fun `should delete an existing meal`() {
        // given
        val mealId = "0"

        // when
        val performDelete = mockMvc.delete("$baseUrl/meals/$mealId")

        // then
        performDelete.andDo { print() }
            .andExpect {
                status { isNoContent() }
            }
    }

    @Test
    fun `should return NOT FOUND if meal to be deleted does not exist`() {
        // given
        val mealId = "tree"

        // when
        val performDelete = mockMvc.delete("$baseUrl/meals/$mealId")

        // then
        performDelete.andDo { print() }
            .andExpect {
                status { isNotFound() }
            }
    }
}*/
