package com.example.core.domain.usecase.admin

import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.admin.AddEmployee
import com.example.core.domain.model.admin.EmployeeLeaveReview
import com.example.core.domain.model.admin.EmployeeSickLeave
import com.example.core.domain.repository.IAdminRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdminInteractor @Inject constructor(private val adminRepository: IAdminRepository) :
    AdminUseCase {
    override suspend fun addEmployee(addEmployeeData: AddEmployee): Flow<ApiResponse<Boolean>> =
        adminRepository.addEmployee(addEmployeeData)

    override suspend fun getEmployeeLeaveReview(): Flow<ApiResponse<List<EmployeeLeaveReview>>> =
        adminRepository.getEmployeeLeaveReview()

    override suspend fun getEmployeeSickLeave(): Flow<ApiResponse<List<EmployeeSickLeave>>> =
        adminRepository.getEmployeeSickLeave()
}