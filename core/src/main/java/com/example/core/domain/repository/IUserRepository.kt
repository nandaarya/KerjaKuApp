package com.example.core.domain.repository

import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
//    suspend fun register(
//        name: String,
//        email: String,
//        password: String
//    ): Flow<ApiResponse<RegisterResponse>>

    suspend fun login(email: String, password: String): Flow<ApiResponse<User>>
    suspend fun saveSession(user: User)
    fun getSession(): Flow<User>
    suspend fun logout()
}