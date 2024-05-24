package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DataAttendanceResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("dataAttendance")
    val dataAttendance: DataAttendanceResult

)

data class DataAttendanceResult(
    @field:SerializedName("id_absen")
    val idAbsen: String,

    @field:SerializedName("id_pegawai")
    val employeeId: String,

    @field:SerializedName("time")
    val time: String,

    @field:SerializedName("clock_in")
    val clockIn: String,

    @field:SerializedName("clock_out")
    val clockOut: String,
)
