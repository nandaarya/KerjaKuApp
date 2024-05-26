package com.example.attendance.ui.attendance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.user.DataAttendance
import com.example.core.domain.model.user.User
import com.example.core.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    fun getSession(): LiveData<User> = userUseCase.getSession().asLiveData()

    fun getDataAttendance(employeeId: String): LiveData<ApiResponse<DataAttendance>> {
        val dataAttendanceLiveData = MutableLiveData<ApiResponse<DataAttendance>>()

        viewModelScope.launch {
            userUseCase.getDataAttendance(employeeId).collect {
                dataAttendanceLiveData.value = it
            }
        }

        return dataAttendanceLiveData
    }

    fun clockOut(employeeId: String, date: String): LiveData<ApiResponse<Boolean>> {
        val clockOutResultLiveData = MutableLiveData<ApiResponse<Boolean>>()

        viewModelScope.launch {
            userUseCase.clockOut(employeeId, date).collect {
                clockOutResultLiveData.value = it
            }
        }

        return clockOutResultLiveData
    }
}