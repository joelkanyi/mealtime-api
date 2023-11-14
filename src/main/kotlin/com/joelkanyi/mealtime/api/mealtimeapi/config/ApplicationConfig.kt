package com.joelkanyi.mealtime.api.mealtimeapi.config

import com.joelkanyi.mealtime.api.mealtimeapi.auth.data.repository.AuthRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ApplicationConfig(
   @Lazy private val authRepository: AuthRepository
) {
    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username ->
            authRepository.findByEmail(username) ?: throw NoSuchElementException("Could not find a user with email $username")
        }
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setUserDetailsService(userDetailsService())
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        return daoAuthenticationProvider
    }

    @Bean
    fun authenticationManager(
        authenticationConfig: AuthenticationConfiguration
    ): AuthenticationManager {
        return authenticationConfig.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}