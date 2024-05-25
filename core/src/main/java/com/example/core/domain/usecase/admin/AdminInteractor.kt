package com.example.core.domain.usecase.admin

import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.AddEmployee
import com.example.core.domain.repository.IAdminRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdminInteractor @Inject constructor(private val adminRepository: IAdminRepository) :
    AdminUseCase {
    override suspend fun addEmployee(addEmployeeData: AddEmployee): Flow<ApiResponse<Boolean>> =
        adminRepository.addEmployee(addEmployeeData)
}