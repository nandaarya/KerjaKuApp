package com.example.kerjakuapp.ui.tambahpegawai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.ActivityRiwayatPengajuanCutiBinding
import com.example.kerjakuapp.databinding.ActivityTambahPegawaiBinding

class TambahPegawaiActivity : AppCompatActivity() {

    lateinit var binding: ActivityTambahPegawaiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahPegawaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Tambah Pegawai"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}