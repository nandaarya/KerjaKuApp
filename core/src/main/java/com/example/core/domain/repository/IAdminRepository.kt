package com.example.core.domain.repository

import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.admin.AddEmployee
import com.example.core.domain.model.admin.EmployeeLeaveReview
import com.example.core.domain.model.admin.EmployeeSickLeave
import com.example.core.domain.model.admin.ReimbursementReview
import kotlinx.coroutines.flow.Flow

interface IAdminRepository {
    suspend fun addEmployee(addEmployeeData: AddEmployee): Flow<ApiResponse<Boolean>>
    suspend fun getEmployeeLeaveReview(): Flow<ApiResponse<List<EmployeeLeaveReview>>>
    suspend fun getEmployeeSickLeave(): Flow<ApiResponse<List<EmployeeSickLeave>>>
    suspend fun getReimbursementReview(): Flow<ApiResponse<List<ReimbursementReview>>>
}