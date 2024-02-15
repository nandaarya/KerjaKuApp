package com.example.kerjakuapp.ui.reviewpengajuancuti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.ActivityReviewPengajuanCutiBinding
import com.example.kerjakuapp.databinding.ActivityTambahPegawaiBinding

class ReviewPengajuanCutiActivity : AppCompatActivity() {

    lateinit var binding: ActivityReviewPengajuanCutiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewPengajuanCutiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Review Pengajuan Cuti"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}