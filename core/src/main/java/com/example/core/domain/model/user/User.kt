package com.example.core.domain.model.user

data class User (
    val userId: String,
    val name: String,
    val role: String,
    val token: String,
    val isLoggedIn: Boolean = false
)