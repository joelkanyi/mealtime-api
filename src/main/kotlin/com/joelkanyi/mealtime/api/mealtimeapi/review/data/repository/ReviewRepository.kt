package com.joelkanyi.mealtime.api.mealtimeapi.review.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.review.data.dto.CreateReviewDto
import com.joelkanyi.mealtime.api.mealtimeapi.review.model.Review

interface ReviewRepository {
    fun getAllReviews(mealId: String): List<Review>
    fun createReview(review: CreateReviewDto, userId: String, mealId: String): String
    fun getReviewById(id: Int): Review?
    fun deleteReview(id: Int)
}