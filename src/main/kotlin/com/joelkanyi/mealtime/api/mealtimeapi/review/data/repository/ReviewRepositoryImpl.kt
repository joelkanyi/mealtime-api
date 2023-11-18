package com.joelkanyi.mealtime.api.mealtimeapi.review.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.UserTable
import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.rowToUserDao
import com.joelkanyi.mealtime.api.mealtimeapi.review.data.database.ReviewTable
import com.joelkanyi.mealtime.api.mealtimeapi.review.data.database.rowToReview
import com.joelkanyi.mealtime.api.mealtimeapi.review.data.dto.CreateReviewDto
import com.joelkanyi.mealtime.api.mealtimeapi.review.model.Review
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class ReviewRepositoryImpl : ReviewRepository {
    private val reviewTable = ReviewTable
    private val userTable = UserTable
    override fun getAllReviews(mealId: String): List<Review> {
        return reviewTable
            .select { reviewTable.meal_id eq mealId }
            .map {
                rowToReview(
                    row = it,
                    user = userTable
                        .select { userTable.id eq it[reviewTable.user_id] }
                        .map(::rowToUserDao)
                        .first()
                )
            }
    }

    override fun createReview(review: CreateReviewDto, userId: String, mealId: String): String {
        reviewTable.insert {
            it[user_id] = userId
            it[meal_id] = mealId
            it[rating] = review.rating
            it[comment] = review.comment
        }
        return "Review created successfully"
    }

    override fun getReviewById(id: Int): Review? {
        return reviewTable
            .select { reviewTable.id eq id }
            .map {
                rowToReview(
                    row = it,
                    user = userTable
                        .select { userTable.id eq it[reviewTable.user_id] }
                        .map(::rowToUserDao)
                        .first()
                )
            }
            .firstOrNull()
    }

    override fun deleteReview(id: Int) {
        reviewTable.deleteWhere { reviewTable.id eq id }
    }
}