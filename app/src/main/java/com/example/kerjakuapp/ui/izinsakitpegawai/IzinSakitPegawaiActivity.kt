package com.example.kerjakuapp.ui.izinsakitpegawai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kerjakuapp.databinding.ActivityIzinSakitPegawaiBinding

class IzinSakitPegawaiActivity : AppCompatActivity() {

    lateinit var binding: ActivityIzinSakitPegawaiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIzinSakitPegawaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Izin Sakit Pegawai"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}