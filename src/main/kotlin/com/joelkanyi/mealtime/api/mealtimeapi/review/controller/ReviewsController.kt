package com.joelkanyi.mealtime.api.mealtimeapi.review.controller

import com.joelkanyi.mealtime.api.mealtimeapi.review.data.dto.CreateReviewDto
import com.joelkanyi.mealtime.api.mealtimeapi.review.model.Review
import com.joelkanyi.mealtime.api.mealtimeapi.review.service.ReviewService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/reviews")
class ReviewsController(
    private val reviewService: ReviewService
) {
    @GetMapping
    fun getAllReviews(): List<Review> {
        return reviewService.getAllReviews()
    }

    @GetMapping("/{id}")
    fun getReviewById(id: Int): Review? {
        return reviewService.getReviewById(id)
    }

    @PostMapping
    fun addReview(
        @RequestBody review: CreateReviewDto,
        @PathVariable("userId") userId: String,
        @PathVariable("mealId") mealId: String
    ) {
        reviewService.addReview(
            review = review,
            userId = userId,
            mealId = mealId
        )
    }
}