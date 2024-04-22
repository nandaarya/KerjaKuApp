package com.example.kerjakuapp.data.model

data class Pegawai(
    val idPegawai: Int,
    val namaPegawai: String,
    val jenisKelamin: String,
    val jabatan: String,
    val departemen: String,
    val tanggalMulaiKerja: String,
    val nomorTelepon: Int,
    val email: String,
    val alamat: String,
    val fotoPegawai: String,
    val sisaCuti: Int,
    val statusPegawai: String
)