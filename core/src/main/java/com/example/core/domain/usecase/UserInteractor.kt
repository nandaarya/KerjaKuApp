package com.example.core.domain.usecase

import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.User
import com.example.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInteractor @Inject constructor(private val userRepository: IUserRepository) : UserUseCase {
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

}