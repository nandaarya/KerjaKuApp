package com.example.core.domain.repository

import com.example.core.data.local.model.UserModel
import com.example.core.data.remote.network.ApiResponse
import com.example.core.data.remote.response.LoginResponse
import com.example.core.data.remote.response.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Flow<ApiResponse<RegisterResponse>>

    suspend fun login(email: String, password: String): Flow<ApiResponse<LoginResponse>>
    suspend fun saveSession(user: UserModel)
    suspend fun getSession(): Flow<UserModel>
    suspend fun logout()
}