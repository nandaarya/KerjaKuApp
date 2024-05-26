package com.example.core.data.remote.response.admin

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.util.Date

class GetEmployeeLeaveReviewResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("employeeLeaveReview")
    val employeeLeaveReview: List<EmployeeLeaveReviewResult>
)

data class EmployeeLeaveReviewResult(
    @field:SerializedName("idPengajuanCuti")
    val idPengajuanCuti: String,

    @field:SerializedName("idPegawai")
    val idPegawai: Int,

    @field:SerializedName("tanggalPengajuan")
    val tanggalPengajuan: Date,

    @field:SerializedName("tanggalMulaiCuti")
    val tanggalMulaiCuti: Date,

    @field:SerializedName("tanggalSelesaiCuti")
    val tanggalSelesaiCuti: String,

    @field:SerializedName("jenisCuti")
    val jenisCuti: String,

    @field:SerializedName("keteranganCuti")
    val keteranganCuti: String,

    @field:SerializedName("bukti")
    val bukti: MultipartBody.Part?,

    @field:SerializedName("statusPengajuan")
    val statusPengajuan: String,

    @field:SerializedName("tanggalPersetujuan")
    val tanggalPersetujuan: Date?,

    @field:SerializedName("pesanPersetujuan")
    val pesanPersetujuan: String
)
