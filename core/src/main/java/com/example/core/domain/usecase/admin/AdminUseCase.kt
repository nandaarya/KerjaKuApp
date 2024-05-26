package com.example.core.domain.usecase.admin

import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.admin.AddEmployee
import com.example.core.domain.model.admin.EmployeeLeaveReview
import kotlinx.coroutines.flow.Flow

interface AdminUseCase {
    suspend fun addEmployee(addEmployeeData: AddEmployee): Flow<ApiResponse<Boolean>>
    suspend fun getEmployeeLeaveReview(): Flow<ApiResponse<List<EmployeeLeaveReview>>>
}