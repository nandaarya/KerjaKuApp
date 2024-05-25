package com.example.core.data.remote.response.admin

import com.google.gson.annotations.SerializedName

data class AddEmployeeResponse(
    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String
)


