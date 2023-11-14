package com.joelkanyi.mealtime.api.mealtimeapi.user.model

import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.User

data class UserData(
    val userId: String?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
) {
    companion object {
        fun User.toUserData(): UserData = UserData(
            userId = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
        )
    }
}
