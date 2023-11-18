package com.joelkanyi.mealtime.api.mealtimeapi.review.service

import com.joelkanyi.mealtime.api.mealtimeapi.review.data.dto.CreateReviewDto
import com.joelkanyi.mealtime.api.mealtimeapi.review.data.repository.ReviewRepository
import com.joelkanyi.mealtime.api.mealtimeapi.review.model.Review
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val reviewRepository: ReviewRepository
) {
    fun getAllReviews(mealId: String): List<Review> {
        return reviewRepository.getAllReviews(mealId)
    }

    fun getReviewById(id: Int): Review? {
        return reviewRepository.getReviewById(id)
    }

    fun addReview(
        review: CreateReviewDto,
        userId: String, mealId: String
    ): String {
        return reviewRepository.createReview(
            review = review,
            userId = userId,
            mealId = mealId
        )
    }

    fun deleteReview(id: Int) {
        reviewRepository.deleteReview(id)
    }
}