package com.example.core.domain.model.user

data class DataAttendance(
    val idAbsen: String,
    val employeeId: String,
    val time: String,
    val clockIn: String,
    val clockOut: String,
)
