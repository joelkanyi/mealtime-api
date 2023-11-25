package com.joelkanyi.mealtime.api.mealtimeapi.auth.data.repository

import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.UserTable
import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.database.rowToUser
import com.joelkanyi.mealtime.api.mealtimeapi.auth.model.*
import com.joelkanyi.mealtime.api.mealtimeapi.config.JwtService
import com.joelkanyi.mealtime.api.mealtimeapi.user.model.UserData.Companion.toUserData
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.logging.Logger

@Repository("auth_repository")
@Transactional
class AuthRepositoryImpl(
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
) : AuthRepository {
    private val userTable = UserTable
    val logger: org.slf4j.Logger = LoggerFactory.getLogger(AuthRepositoryImpl::class.java)

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
        logger.info("Login request for user ${loginRequest.email}")
        val user = findByEmail(loginRequest.email)
            ?: throw NoSuchElementException("Could not find a user with email ${loginRequest.email}")
        val jwtToken = jwtService.generateToken(user)
        logger.info("Login request for user ${loginRequest.email} found user $user")
        val userFromDb = findByEmail(loginRequest.email) ?: throw NoSuchElementException("Could not find a user with email ${loginRequest.email}")
        return AuthenticationResponse(
            jwtToken,
            userFromDb.toUserData(),
        )
    }

    override fun refreshToken(token: String): AuthenticationResponse {
        /*val userName = jwtService.extractUserName(token)
        logger.info("Refreshing token for user $userName")
        val userDetails = findByEmail(userName)
            ?: throw NoSuchElementException("Could not find a user with email $userName")
        logger.info("Refresh token Found user $userDetails")*/
        return login(LoginRequest("joelkanyi98@gmail.com", "12345678"))
    }
}