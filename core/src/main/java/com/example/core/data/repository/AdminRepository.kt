package com.example.core.data.repository

import com.example.core.data.local.LocalDataSource
import com.example.core.data.remote.RemoteDataSource
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.AddEmployee
import com.example.core.domain.repository.IAdminRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdminRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) : IAdminRepository {
    override suspend fun addEmployee(addEmployeeData: AddEmployee): Flow<ApiResponse<Boolean>> = remoteDataSource.addEmployee(addEmployeeData)

}