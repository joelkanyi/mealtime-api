package com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database

import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.Role
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.User
import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.dto.UserDto
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object UserTable : Table("user") {
    val id = varchar("id", 255)
    override val primaryKey = PrimaryKey(id, name = "user_pk")
    val firstName = varchar("first_name", 255)
    val lastName = varchar("last_name", 255)
    val email = varchar("email", 255)
    val password = varchar("password", 255)
    val role = varchar("role", 255)
}

fun rowToUser(row: ResultRow): User {
    return User(
        id = row[UserTable.id],
        firstName = row[UserTable.firstName],
        lastName = row[UserTable.lastName],
        email = row[UserTable.email],
        passw = row[UserTable.password],
        role = Role.valueOf(row[UserTable.role])
    )
}

fun rowToUserDao(row: ResultRow): UserDto {
    return UserDto(
        id = row[UserTable.id],
        firstName = row[UserTable.firstName],
        lastName = row[UserTable.lastName],
        email = row[UserTable.email],
    )
}