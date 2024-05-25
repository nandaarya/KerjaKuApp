package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ClockInResponse(
    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,
)
