package com.example.core.domain.model.admin

import okhttp3.MultipartBody
import java.util.Date

data class EmployeeSickLeave(
    val idIzinSakit: Int,
    val idPegawai: Int,
    val tanggalPengajuan: Date,
    val tanggalMulai: Date,
    val tanggalSelesai: Date,
    val keteranganSakit: String,
    val bukti: MultipartBody.Part,
    val statusPengajuan: String,
    val tanggalPersetujuan: Date?,
    val pesanPersetujuan: String?
)
