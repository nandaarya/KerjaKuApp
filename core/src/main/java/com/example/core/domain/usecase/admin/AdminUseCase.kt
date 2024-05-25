package com.example.core.domain.usecase.admin

import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.AddEmployee
import kotlinx.coroutines.flow.Flow

interface AdminUseCase {
    suspend fun addEmployee(addEmployeeData: AddEmployee): Flow<ApiResponse<Boolean>>
}