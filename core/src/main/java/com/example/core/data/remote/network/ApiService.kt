package com.example.core.data.remote.network

import com.example.core.data.remote.response.admin.AddEmployeeResponse
import com.example.core.data.remote.response.user.ClockInResponse
import com.example.core.data.remote.response.user.ClockOutResponse
import com.example.core.data.remote.response.user.DataAttendanceResponse
import com.example.core.data.remote.response.user.LoginResponse
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @GET("/absen/{employeeId}")
    suspend fun getDataAttendance(
        @Path("employeeId") employeeId: String,
    ): DataAttendanceResponse

    @FormUrlEncoded
    @POST("/absen/clock-in")
    suspend fun clockIn(
        @Field("id_pegawai") employeeId: String,
        @Part file: MultipartBody.Part
    ): ClockInResponse

    @FormUrlEncoded
    @POST("/absen/clock-out")
    suspend fun clockOut(
        @Field("id_pegawai") idPegawai: String,
        @Field("tanggal_absen") tanggalAbsen: String
    ): ClockOutResponse

    @FormUrlEncoded
    @POST("/admin/add-employee")
    suspend fun addEmployee(
        @Field("employee_name") employeeName: String,
        @Field("gender") gender: String,
        @Field("position") position: String,
        @Field("department") department: String,
        @Field("start_date") startDate: String,
        @Field("phone_number") phoneNumber: Int,
        @Field("email") email: String,
        @Field("address") address: String,
        @Field("role") role: String,
        @Part employeePhoto: MultipartBody.Part
    ): AddEmployeeResponse
}