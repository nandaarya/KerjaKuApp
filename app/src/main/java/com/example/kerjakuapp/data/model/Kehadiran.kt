package com.example.kerjakuapp.data.model

data class Kehadiran(
    val idKehadiran: Int,
    val idPegawai: Int,
    val tanggal: String,
    val jamMasuk: String,
    val jamKeluar: String?,
    val status: String
)
