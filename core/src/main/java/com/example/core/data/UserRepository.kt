package com.example.core.data

import com.example.core.data.local.LocalDataSource
import com.example.core.data.remote.RemoteDataSource
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.DataAttendance
import com.example.core.domain.model.User
import com.example.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) : IUserRepository {
    override suspend fun login(email: String, password: String): Flow<ApiResponse<User>> =
        remoteDataSource.login(email, password)

    override suspend fun saveSession(user: User) = localDataSource.saveSession(user)
    override fun getSession(): Flow<User> = localDataSource.getSession()
    override suspend fun logout() = localDataSource.logout()
    override suspend fun getDataAttendance(employeeId: String): Flow<ApiResponse<DataAttendance>> =
        remoteDataSource.getDataAttendance(employeeId)
}