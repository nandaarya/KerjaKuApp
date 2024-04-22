package com.example.kerjakuapp.data.model

data class Reimbursement(
    val idReimbursement: Int,
    val idPegawai: Int,
    val tanggalPengajuan: String,
    val jenisReimbursement: String,
    val deskripsi: String,
    val jumlahPengeluaran: Double,
    val bukti: String,
    val statusPengajuan: String,
    val tanggalPersetujuan: String
)