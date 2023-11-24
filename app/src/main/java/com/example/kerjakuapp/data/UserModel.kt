package com.example.kerjakuapp.data

data class UserModel(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)