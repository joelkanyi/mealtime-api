package com.joelkanyi.mealtime.api.mealtimeapi.config

import com.joelkanyi.mealtime.api.mealtimeapi.utils.DelegatedAuthEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true
)
class SecurityConfig(
    val authenticationProvider: AuthenticationProvider,
    val jwtAuthenticationFilter: JwtAuthenticationFilter,
    val authenticationEntryPoint: DelegatedAuthEntryPoint
) {
    @Bean
    fun securityFilterChain(
        http: HttpSecurity
    ): SecurityFilterChain {
        return  http
            .csrf()
            .disable()
            .cors()
            .disable()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/api/auth/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

            .orBuild
    }
}