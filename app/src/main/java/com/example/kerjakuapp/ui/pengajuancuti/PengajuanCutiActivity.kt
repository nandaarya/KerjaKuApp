package com.example.kerjakuapp.ui.pengajuancuti

import android.app.DatePickerDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kerjakuapp.databinding.ActivityPengajuanCutiBinding
import com.example.kerjakuapp.utils.getImageUri
import java.util.Calendar

class PengajuanCutiActivity : AppCompatActivity() {

    lateinit var binding: ActivityPengajuanCutiBinding
    private var currentImageUri: Uri? = null
    private var selectedStartDate: String = ""
    private var selectedEndDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengajuanCutiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Pengajuan Cuti"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val jenisCutiList = listOf(
            "Cuti untuk ambil Hak Cuti",
            "Cuti karena alasan penting",
            "Cuti melahirkan",
            "Cuti menikah",
            "Cuti untuk keperluan dinas"
        )
        binding.spinnerJenisCuti.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, jenisCutiList)

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

        binding.btnAjukanCuti.setOnClickListener {
            val jenisCuti = binding.spinnerJenisCuti.selectedItem.toString()
            val keterangan = binding.edtKeteranganCuti.text.toString()

            // Simpan data ajukan cuti
            Log.d(
                "button",
                "Ajukan Cuti. Data: $jenisCuti, $selectedStartDate, $selectedEndDate, $keterangan"
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
        launcherIntentCamera.launch(currentImageUri)
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