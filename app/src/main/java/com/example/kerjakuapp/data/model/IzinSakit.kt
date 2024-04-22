package com.example.kerjakuapp.data.model

data class IzinSakit(
    val idIzinSakit: Int,
    val idPegawai: Int,
    val tanggalPengajuan: String, // Tanggal pengajuan bisa otomatis di-generate oleh sistem
    val tanggalMulai: String,
    val tanggalSelesai: String,
    val keteranganSakit: String,
    val bukti: String, // Path atau URL ke bukti izin sakit
    val statusPengajuan: String,
    val tanggalPersetujuan: String?,
    val pesanPersetujuan: String?
)
