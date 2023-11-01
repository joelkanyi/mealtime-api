package com.joelkanyi.mealtime.api.mealtimeapi.auth.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val passw: String,
    val role: Role?,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return listOf(
            GrantedAuthority { role?.name }
        ).toMutableList()
    }

    override fun getPassword(): String {
        return passw
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}

enum class Role {
    USER,
    ADMIN
}