package com.example.core.domain.model.admin

import okhttp3.MultipartBody
import java.util.Date

data class ReimbursementReview(
    val idReimbursement: Int,
    val idPegawai: Int,
    val tanggalPengajuan: Date,
    val jenisReimbursement: String,
    val deskripsi: String,
    val jumlahPengeluaran: Int,
    val bukti: MultipartBody.Part,
    val statusPengajuan: String,
    val tanggalPersetujuan: Date?
)