package com.example.core.data.repository

import com.example.core.data.local.LocalDataSource
import com.example.core.data.remote.RemoteDataSource
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.admin.AddEmployee
import com.example.core.domain.model.admin.EmployeeLeaveReview
import com.example.core.domain.model.admin.EmployeeSickLeave
import com.example.core.domain.model.admin.FindEmployeeByEmployeeName
import com.example.core.domain.model.admin.ReimbursementReview
import com.example.core.domain.repository.IAdminRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdminRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) : IAdminRepository {
    override suspend fun addEmployee(addEmployeeData: AddEmployee): Flow<ApiResponse<Boolean>> =
        remoteDataSource.addEmployee(addEmployeeData)

    override suspend fun getEmployeeLeaveReview(): Flow<ApiResponse<List<EmployeeLeaveReview>>> =
        remoteDataSource.getEmployeeLeaveReview()

    override suspend fun getEmployeeSickLeave(): Flow<ApiResponse<List<EmployeeSickLeave>>> =
        remoteDataSource.getEmployeeSickLeave()

    override suspend fun getReimbursementReview(): Flow<ApiResponse<List<ReimbursementReview>>> =
        remoteDataSource.getReimbursementReview()

    override suspend fun findEmployeeByEmployeeName(name: String): Flow<ApiResponse<List<FindEmployeeByEmployeeName>>> =
        remoteDataSource.findEmployeeByEmployeeName(name)


}