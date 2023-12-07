package com.example.kerjakuapp.ui.reimbursement

import android.app.DatePickerDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.ActivityReimbursementBinding
import com.example.kerjakuapp.utils.getImageUri
import java.util.Calendar

class ReimbursementActivity : AppCompatActivity() {

    lateinit var binding: ActivityReimbursementBinding
    private var currentImageUri: Uri? = null
    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReimbursementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Reimbursement"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set jenis pengeluaran
        val jenisPengeluaranList = listOf("Transportasi", "Makanan", "Akomodasi", "Lain-lain")
        binding.spinnerJenisPengeluaran.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, jenisPengeluaranList)

        val edtNominalPengeluaran = findViewById<EditText>(R.id.edt_nominal_pengeluaran)
        val keteranganEditText = findViewById<EditText>(R.id.edt_keterangan)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        selectedDate = (day.toString() + "-" + (month + 1) + "-" + year)
        binding.tvSelectedDate.text = selectedDate

        binding.btnDatePicker.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    selectedDate = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.tvSelectedDate.text = selectedDate
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        binding.btnSimpan.setOnClickListener {
            val jenisPengeluaran = binding.spinnerJenisPengeluaran.selectedItem.toString()
            val nominalPengeluaran = edtNominalPengeluaran.text.toString().toInt()
            val keterangan = keteranganEditText.text.toString()

            // Simpan data reimbursement
            Log.d(
                "button",
                "Ajukan Reimbursement. Data: $jenisPengeluaran, $nominalPengeluaran, $keterangan, $selectedDate"
            )
        }

        binding.btnUploadBukti.setOnClickListener { startCamera() }
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