package com.example.core.data.remote.response.admin

import com.google.gson.annotations.SerializedName

data class FindEmployeeByEmployeeNameResponse (
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("findEmployeeByEmployeeNameResult")
    val findEmployeeByEmployeeNameResult: List<FindEmployeeByEmployeeNameResult>
)

data class FindEmployeeByEmployeeNameResult (
    @field:SerializedName("employeeId")
    val employeeId: String,

    @field:SerializedName("employeeName")
    val employeeName: String,

    @field:SerializedName("department")
    val department: String
)
