package com.example.kerjakuapp.ui.tambahpegawai

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kerjakuapp.databinding.ActivityTambahPegawaiBinding
import java.util.Calendar

class TambahPegawaiActivity : AppCompatActivity() {

    lateinit var binding: ActivityTambahPegawaiBinding
    private var selectedTanggalMulaiKerja: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahPegawaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Tambah Pegawai"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Initial start and End Date
        selectedTanggalMulaiKerja = (day.toString() + "-" + (month + 1) + "-" + year)
        binding.tvSelectedStartDate.text = selectedTanggalMulaiKerja

        binding.btnTanggalMulaiKerjaPicker.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    selectedTanggalMulaiKerja = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.tvSelectedStartDate.text = selectedTanggalMulaiKerja
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}