package com.example.core.domain.model.admin

import okhttp3.MultipartBody

data class AddEmployee(
    val employeeName: String,
    val gender: String,
    val position: String,
    val department: String,
    val startDate: String,
    val phoneNumber: Int,
    val email: String,
    val address: String,
    val employeePhoto: MultipartBody.Part,
    val role: String
)
