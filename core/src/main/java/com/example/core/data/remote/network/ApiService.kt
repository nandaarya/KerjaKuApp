package com.example.core.data.remote.network

import com.example.core.data.remote.response.ClockInResponse
import com.example.core.data.remote.response.ClockOutResponse
import com.example.core.data.remote.response.DataAttendanceResponse
import com.example.core.data.remote.response.LoginResponse
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
//    @FormUrlEncoded
//    @POST("register")
//    suspend fun register(
//        @Field("name") name: String,
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): RegisterResponse

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
        @Part file : MultipartBody.Part
    ): ClockInResponse

    @FormUrlEncoded
    @POST("/absen/clock-out")
    suspend fun clockOut(
        @Field("id_pegawai") idPegawai: String,
        @Field("tanggal_absen") tanggalAbsen: String
    ): ClockOutResponse
}