package com.example.core.data.local.model

data class UserModel(
    val userId: String,
    val name: String,
    val role: String,
    val token: String,
    val isLoggedIn: Boolean = false
)