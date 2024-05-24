package com.example.core.data.remote.network

import com.example.core.data.remote.response.DataAttendanceResponse
import com.example.core.data.remote.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
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

}