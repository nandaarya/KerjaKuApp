package com.example.attendance.ui.clockin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ClockInViewModel @Inject constructor(private val userUseCase: UserUseCase): ViewModel() {
    fun clockIn(employeeId: String, file: MultipartBody.Part): LiveData<ApiResponse<Boolean>> {
        val clockInResultLiveData = MutableLiveData<ApiResponse<Boolean>>()

        viewModelScope.launch {
            userUseCase.clockIn(employeeId, file).collect {
                clockInResultLiveData.value = it
            }
        }

        return clockInResultLiveData
    }
}