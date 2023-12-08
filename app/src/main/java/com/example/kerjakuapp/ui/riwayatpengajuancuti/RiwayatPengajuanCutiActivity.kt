package com.example.kerjakuapp.ui.riwayatpengajuancuti

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kerjakuapp.databinding.ActivityRiwayatPengajuanCutiBinding

class RiwayatPengajuanCutiActivity : AppCompatActivity() {

    lateinit var binding: ActivityRiwayatPengajuanCutiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatPengajuanCutiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Riwayat Pengajuan Cuti"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}