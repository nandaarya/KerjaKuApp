package com.example.kerjakuapp.ui.tambahpegawai

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kerjakuapp.databinding.ActivityTambahPegawaiBinding
import java.util.Calendar

class TambahPegawaiActivity : AppCompatActivity() {

    lateinit var binding: ActivityTambahPegawaiBinding
    private var selectedTanggalMulaiKerja: String = ""
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahPegawaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Tambah Pegawai"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnTanggalMulaiKerjaPicker.setOnClickListener { openDatePicker() }
        binding.btnUploadFotoPegawai.setOnClickListener { startGallery() }
        binding.btnTambahPegawai.setOnClickListener { tambahPegawai() }
    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Initial start and End Date
        selectedTanggalMulaiKerja = (day.toString() + "-" + (month + 1) + "-" + year)
        binding.tvSelectedStartDate.text = selectedTanggalMulaiKerja

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

//    private fun startGallery() {
//        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//    }
//
//    private val launcherGallery = registerForActivityResult(
//        ActivityResultContracts.PickVisualMedia()
//    ) { uri: Uri? ->
//        if (uri != null) {
//            currentImageUri = uri
//            showImage()
//        } else {
//            Log.d("Photo Picker", "No media selected")
//        }
//    }

    private fun startGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        launcherGallery.launch(intent)
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri: Uri? = result.data?.data
            if (uri != null) {
                currentImageUri = uri
                showImage()
            } else {
                Log.d("Photo Picker", "No media selected")
            }
        } else {
            Log.d("Photo Picker", "Gallery activity result not OK")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivPreview.setImageURI(it)
        }
    }

    private fun tambahPegawai() {

    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}