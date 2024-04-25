package com.example.core.domain.usecase

import com.example.core.data.local.model.UserModel
import com.example.core.data.remote.network.ApiResponse
import com.example.core.data.remote.response.LoginResponse
import com.example.core.data.remote.response.RegisterResponse
import com.example.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {
    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Flow<ApiResponse<RegisterResponse>> = userRepository.register(name, email, password)

    override suspend fun login(email: String, password: String): Flow<ApiResponse<LoginResponse>> =
        userRepository.login(email, password)

    override suspend fun saveSession(user: UserModel) = userRepository.saveSession(user)

    override suspend fun getSession(): Flow<UserModel> = userRepository.getSession()

    override suspend fun logout() = userRepository.logout()

}