package com.example.core.data.remote.response.admin

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.util.Date

data class GetEmployeeSickLeaveResponse (
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("employeeSickLeave")
    val employeeSickLeave: List<EmployeeSickLeaveResult>
)

data class EmployeeSickLeaveResult (
    @field:SerializedName("idIzinSakit")
    val idIzinSakit: Int,

    @field:SerializedName("idPegawai")
    val idPegawai: Int,

    @field:SerializedName("tanggalPengajuan")
    val tanggalPengajuan: Date,

    @field:SerializedName("tanggalMulai")
    val tanggalMulai: Date,

    @field:SerializedName("tanggalSelesai")
    val tanggalSelesai: Date,

    @field:SerializedName("keteranganSakit")
    val keteranganSakit: String,

    @field:SerializedName("bukti")
    val bukti: MultipartBody.Part,

    @field:SerializedName("statusPengajuan")
    val statusPengajuan: String,

    @field:SerializedName("tanggalPersetujuan")
    val tanggalPersetujuan: Date?,

    @field:SerializedName("pesanPersetujuan")
    val pesanPersetujuan: String?
)
