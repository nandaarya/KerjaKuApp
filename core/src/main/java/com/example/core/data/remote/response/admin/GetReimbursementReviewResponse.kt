package com.example.core.data.remote.response.admin

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.util.Date

data class GetReimbursementReviewResponse (
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("reimbursementReview")
    val reimbursementReview: List<ReimbursementReviewResult>
)

data class ReimbursementReviewResult (
    @field:SerializedName("idReimbursement")
    val idReimbursement: Int,

    @field:SerializedName("idPegawai")
    val idPegawai: Int,

    @field:SerializedName("tanggalPengajuan")
    val tanggalPengajuan: Date,

    @field:SerializedName("jenisReimbursement")
    val jenisReimbursement: String,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("jumlahPengeluaran")
    val jumlahPengeluaran: Int,

    @field:SerializedName("bukti")
    val bukti: MultipartBody.Part,

    @field:SerializedName("statusPengajuan")
    val statusPengajuan: String,

    @field:SerializedName("tanggalPersetujuan")
    val tanggalPersetujuan: Date?
)
