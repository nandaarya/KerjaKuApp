package com.example.core.domain.model.admin

import okhttp3.MultipartBody
import java.util.Date

data class EmployeeLeaveReview(
    val idPengajuanCuti: String,
    val idPegawai: Int,
    val tanggalPengajuan: Date,
    val tanggalMulaiCuti: Date,
    val tanggalSelesaiCuti: String,
    val jenisCuti: String,
    val keteranganCuti: String,
    val bukti: MultipartBody.Part?,
    val statusPengajuan: String,
    val tanggalPersetujuan: Date?,
    val pesanPersetujuan: String
)
