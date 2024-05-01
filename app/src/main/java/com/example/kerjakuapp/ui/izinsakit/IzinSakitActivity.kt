package com.example.kerjakuapp.ui.izinsakit

import android.app.DatePickerDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kerjakuapp.databinding.ActivityIzinSakitBinding
import com.example.kerjakuapp.utils.getImageUri
import java.util.Calendar

class IzinSakitActivity : AppCompatActivity() {

    lateinit var binding: ActivityIzinSakitBinding
    private var currentImageUri: Uri? = null
    private var selectedStartDate: String = ""
    private var selectedEndDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIzinSakitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Izin Sakit"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Initial start and End Date
        selectedStartDate = (day.toString() + "-" + (month + 1) + "-" + year)
        selectedEndDate = (day.toString() + "-" + (month + 1) + "-" + year)
        binding.tvSelectedStartDate.text = selectedStartDate
        binding.tvSelectedEndDate.text = selectedEndDate

        binding.btnStartDatePicker.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    selectedStartDate = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.tvSelectedStartDate.text = selectedStartDate
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        binding.btnEndDatePicker.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    selectedEndDate = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.tvSelectedEndDate.text = selectedEndDate
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        binding.btnUploadBukti.setOnClickListener { startCamera() }

        binding.btnIzinSakit.setOnClickListener {
            val keterangan = binding.edtKeteranganSakit.text.toString()

            // Simpan data ajukan cuti
            Log.d(
                "button",
                "Izin Sakit. Data: $selectedStartDate, $selectedEndDate, $keterangan"
            )
        }
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri!!)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            binding.ivPreviewBukti.setImageURI(it)
        }
    }
}