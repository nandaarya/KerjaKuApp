package com.example.kerjakuapp.data.model

data class Cuti(
    val idPengajuanCuti: String,
    val idPegawai: Int,
    val tanggalPengajuan: String,
    val tanggalMulaiCuti: String,
    val tanggalSelesaiCuti: String,
    val jenisCuti: String,
    val keteranganCuti: String,
    val bukti: String,
    val statusPengajuan: String,
    val tanggalPersetujuan: String,
    val pesanPersetujuan: String
)