package com.example.core.data.remote

import android.util.Log
import com.example.core.data.local.LocalDataSource
import com.example.core.data.remote.network.ApiResponse
import com.example.core.data.remote.network.ApiService
import com.example.core.domain.model.admin.AddEmployee
import com.example.core.domain.model.admin.EmployeeLeaveReview
import com.example.core.domain.model.admin.EmployeeSickLeave
import com.example.core.domain.model.admin.FindEmployeeByEmployeeName
import com.example.core.domain.model.admin.ReimbursementReview
import com.example.core.domain.model.user.DataAttendance
import com.example.core.domain.model.user.User
import com.example.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) {
    suspend fun login(email: String, password: String): Flow<ApiResponse<User>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.login(email, password).loginResult
                Log.d("login in data source", response.toString())
                Log.d("login in data source", "$email, $password")
                val dataArray = DataMapper.mapLoginResponseToDomain(response)
                localDataSource.saveSession(dataArray)
                emit(ApiResponse.Success(dataArray))
            } catch (e: Exception) {
                if ((e is HttpException) && (e.code() == 404)) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(e.toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDataAttendance(employeeId: String): Flow<ApiResponse<DataAttendance>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.getDataAttendance(employeeId).dataAttendance
                val dataArray = DataMapper.mapDataAttendanceResponseToDomain(response)
                emit(ApiResponse.Success(dataArray))
            } catch (e: Exception) {
                if ((e is HttpException) && (e.code() == 404)) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(e.toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    fun clockIn(employeeId: String, file: MultipartBody.Part): Flow<ApiResponse<Boolean>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.clockIn(employeeId, file).success
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                if ((e is HttpException) && (e.code() == 404)) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(e.toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    fun clockOut(employeeId: String, date: String): Flow<ApiResponse<Boolean>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.clockOut(employeeId, date).success
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                if ((e is HttpException) && (e.code() == 404)) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(e.toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    fun addEmployee(addEmployeeData: AddEmployee): Flow<ApiResponse<Boolean>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.addEmployee(
                    addEmployeeData.employeeName,
                    addEmployeeData.gender,
                    addEmployeeData.position,
                    addEmployeeData.department,
                    addEmployeeData.startDate,
                    addEmployeeData.phoneNumber,
                    addEmployeeData.email,
                    addEmployeeData.address,
                    addEmployeeData.role,
                    addEmployeeData.employeePhoto,

                    ).success
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                if ((e is HttpException) && (e.code() == 404)) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(e.toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getEmployeeLeaveReview(): Flow<ApiResponse<List<EmployeeLeaveReview>>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.getEmployeeLeaveReview().employeeLeaveReview
                val dataArray = response.map {
                    DataMapper.mapEmployeeLeaveReviewResponseToDomain(it)
                }
                emit(ApiResponse.Success(dataArray))
            } catch (e: Exception) {
                if ((e is HttpException) && (e.code() == 404)) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(e.toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getEmployeeSickLeave(): Flow<ApiResponse<List<EmployeeSickLeave>>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.getEmployeeSickLeave().employeeSickLeave
                val dataArray = response.map {
                    DataMapper.mapEmployeeSickLeaveResponseToDomain(it)
                }
                emit(ApiResponse.Success(dataArray))
            } catch (e: Exception) {
                if ((e is HttpException) && (e.code() == 404)) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(e.toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getReimbursementReview(): Flow<ApiResponse<List<ReimbursementReview>>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiService.getReimbursementReview().reimbursementReview
                val dataArray = response.map {
                    DataMapper.mapReimbursementReviewResponseToDomain(it)
                }
                emit(ApiResponse.Success(dataArray))
            } catch (e: Exception) {
                if ((e is HttpException) && (e.code() == 404)) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(e.toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    fun findEmployeeByEmployeeName(name: String): Flow<ApiResponse<List<FindEmployeeByEmployeeName>>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response =
                    apiService.findEmployeeByEmployeeName(name).findEmployeeByEmployeeNameResult
                val dataArray = response.map {
                    DataMapper.mapFindEmployeeByEmployeeNameResponseToDomain(it)
                }
                emit(ApiResponse.Success(dataArray))
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