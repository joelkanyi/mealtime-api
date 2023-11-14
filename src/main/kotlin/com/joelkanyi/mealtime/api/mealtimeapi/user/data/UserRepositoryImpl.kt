package com.joelkanyi.mealtime.api.mealtimeapi.user.data

import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.UserTable
import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.rowToUser
import com.joelkanyi.mealtime.api.mealtimeapi.user.model.UserData
import com.joelkanyi.mealtime.api.mealtimeapi.user.model.UserData.Companion.toUserData
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository("user-repository")
@Transactional
class UserRepositoryImpl : UserRepository {
    private val userTable = UserTable

    override fun getUser(userId: String): UserData {
        return userTable.select { userTable.id eq userId }.map { rowToUser(it) }.firstOrNull()?.toUserData()
            ?: throw NoSuchElementException("Could not find a user with id $userId")
    }
}