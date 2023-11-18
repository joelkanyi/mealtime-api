package com.joelkanyi.mealtime.api.mealtimeapi.review.controller

import com.joelkanyi.mealtime.api.mealtimeapi.review.data.dto.CreateReviewDto
import com.joelkanyi.mealtime.api.mealtimeapi.review.model.Review
import com.joelkanyi.mealtime.api.mealtimeapi.review.service.ReviewService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/reviews")
class ReviewsController(
    private val reviewService: ReviewService
) {
    @GetMapping("/{mealId}")
    fun getAllReviews(
        @PathVariable("mealId") mealId: String
    ): List<Review> {
        return reviewService.getAllReviews(mealId)
    }

    @GetMapping("/{id}")
    fun getReviewById(id: Int): Review? {
        return reviewService.getReviewById(id)
    }

    @PostMapping("/{userId}/{mealId}")
    @ResponseStatus(HttpStatus.CREATED)
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteReview(@PathVariable("id") id: Int) {
        reviewService.deleteReview(id)
    }
}