package com.example.core.domain.usecase.user

import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.DataAttendance
import com.example.core.domain.model.User
import com.example.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInteractor @Inject constructor(private val userRepository: IUserRepository) :
    UserUseCase {
//    override suspend fun register(
//        name: String,
//        email: String,
//        password: String
//    ): Flow<ApiResponse<RegisterResponse>> = userRepository.register(name, email, password)

    override suspend fun login(email: String, password: String): Flow<ApiResponse<User>> =
        userRepository.login(email, password)

    override suspend fun saveSession(user: User) = userRepository.saveSession(user)

    override fun getSession(): Flow<User> = userRepository.getSession()

    override suspend fun logout() = userRepository.logout()
    override suspend fun getDataAttendance(employeeId: String): Flow<ApiResponse<DataAttendance>> =
        userRepository.getDataAttendance(employeeId)

    override suspend fun clockIn(
        employeeId: String, file: MultipartBody.Part
    ): Flow<ApiResponse<Boolean>> = userRepository.clockIn(employeeId, file)

    override suspend fun clockOut(
        employeeId: String, date: String
    ): Flow<ApiResponse<Boolean>> = userRepository.clockOut(employeeId, date)
}