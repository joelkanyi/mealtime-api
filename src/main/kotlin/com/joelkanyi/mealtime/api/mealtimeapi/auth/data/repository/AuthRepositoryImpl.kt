package com.joelkanyi.mealtime.api.mealtimeapi.auth.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.UserTable
import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.rowToUser
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.*
import com.joelkanyi.mealtime.api.mealtimeapi.user.model.UserData.Companion.toUserData
import com.joelkanyi.mealtime.api.mealtimeapi.config.JwtService
import com.joelkanyi.mealtime.api.mealtimeapi.user.model.UserData
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.NoSuchElementException

@Repository("auth_repository")
@Transactional
class AuthRepositoryImpl(
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
) : AuthRepository {
    private val userTable = UserTable
    override fun findByEmail(email: String): User? {
        return userTable.select { userTable.email eq email }.map { rowToUser(it) }.firstOrNull()
            ?: throw NoSuchElementException("Could not find a user with email $email")
    }

    override fun register(registerRequest: RegisterRequest): AuthenticationResponse {
        val users = userTable.selectAll().map { rowToUser(it) }
        if (users.any { it.email == registerRequest.email }) {
            throw IllegalArgumentException("User with email ${registerRequest.email} already exists")
        }
        val userId = UUID.randomUUID().toString()
        val user = org.springframework.security.core.userdetails.User.builder()
            .username(registerRequest.email)
            .password(passwordEncoder.encode(registerRequest.password))
            .roles(Role.USER.name)
            .build()

        userTable.insert {
            it[id] = userId
            it[firstName] = registerRequest.firstName
            it[lastName] = registerRequest.lastName
            it[email] = registerRequest.email
            it[password] = passwordEncoder.encode(registerRequest.password)
            it[role] = Role.USER.name
        }

        val jwtToken = jwtService.generateToken(user)
        val userFromDb = findByEmail(registerRequest.email) ?: throw NoSuchElementException("Could not find a user with email ${registerRequest.email}")
        return AuthenticationResponse(
            jwtToken,
            userFromDb.toUserData(),
        )
    }

    override fun login(loginRequest: LoginRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.email,
                loginRequest.password
            )
        )
        val user = findByEmail(loginRequest.email)
            ?: throw NoSuchElementException("Could not find a user with email ${loginRequest.email}")
        val jwtToken = jwtService.generateToken(user)
        val userFromDb = findByEmail(loginRequest.email) ?: throw NoSuchElementException("Could not find a user with email ${loginRequest.email}")
        return AuthenticationResponse(
            jwtToken,
            userFromDb.toUserData(),
        )
    }

    override fun refreshToken(token: String): AuthenticationResponse {
        val userName = jwtService.extractUserName(token)
        val userDetails = findByEmail(userName)
            ?: throw NoSuchElementException("Could not find a user with email $userName")
        return login(LoginRequest(userDetails.email, userDetails.password))
    }
}