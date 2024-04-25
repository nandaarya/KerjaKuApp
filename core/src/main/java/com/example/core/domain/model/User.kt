package com.example.core.domain.model

data class User (
    val email: String,
    val password: String,
    val token: String? = null,
    val isLoggedIn: Boolean = false,
    val isClockedIn: Boolean = false,
    val isClockedOut: Boolean = false,
)