package com.example.core.data.remote

import android.util.Log
import com.example.core.data.remote.network.ApiResponse
import com.example.core.data.remote.network.ApiService
import com.example.core.domain.model.User
import com.example.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun login(email: String, password: String): Flow<ApiResponse<User>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.login(email, password).loginResult
                Log.d("login in data source", response.toString())
                Log.d("login in data source", "$email, $password")
                val dataArray = DataMapper.mapResponseToDomain(response)
                if (dataArray != null) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                if ((e is HttpException) && (e.code() == 404)) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(e.toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}