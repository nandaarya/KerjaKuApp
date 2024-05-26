package com.example.core.domain.repository

import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.user.DataAttendance
import com.example.core.domain.model.user.User
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

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
    suspend fun getDataAttendance(employeeId: String): Flow<ApiResponse<DataAttendance>>
    suspend fun clockIn(employeeId: String, file: MultipartBody.Part): Flow<ApiResponse<Boolean>>
    suspend fun clockOut(employeeId: String, date: String): Flow<ApiResponse<Boolean>>
}