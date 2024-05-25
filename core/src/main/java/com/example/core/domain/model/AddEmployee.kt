package com.example.core.domain.model

data class AddEmployee(
    val employeeName: String,
    val gender: String,
    val position: String,
    val department: String,
    val startDate: String,
    val phoneNumber: Int,
    val email: String,
    val address: String,
    val employeePhoto: String,
    val role: String
)
