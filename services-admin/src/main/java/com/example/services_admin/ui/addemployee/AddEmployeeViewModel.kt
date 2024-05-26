package com.example.services_admin.ui.addemployee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.admin.AddEmployee
import com.example.core.domain.usecase.admin.AdminUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEmployeeViewModel @Inject constructor(private val adminUseCase: AdminUseCase) :
    ViewModel() {

    fun addEmployee(addEmployeeData: AddEmployee): LiveData<ApiResponse<Boolean>> {
        val addEmployeeResultLiveData = MutableLiveData<ApiResponse<Boolean>>()

        viewModelScope.launch {
            adminUseCase.addEmployee(addEmployeeData).collect {
                addEmployeeResultLiveData.value = it
            }
        }

        return addEmployeeResultLiveData
    }

}