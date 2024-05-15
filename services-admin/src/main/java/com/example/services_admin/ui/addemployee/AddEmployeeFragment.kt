package com.example.services_admin.ui.addemployee

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.services_admin.databinding.FragmentAddEmployeeBinding
import java.util.Calendar

class AddEmployeeFragment : Fragment() {
    private var _binding: FragmentAddEmployeeBinding? = null
    private val binding get() = _binding

    private var selectedTanggalMulaiKerja: String = ""
    private var currentImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ScrollView? {
        _binding = FragmentAddEmployeeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext() as AppCompatActivity).supportActionBar?.apply {
            title = "Tambah Pegawai"
            setDisplayHomeAsUpEnabled(true)
        }

        setupButton()
    }

    private fun setupButton() {
        binding?.btnTanggalMulaiKerjaPicker?.setOnClickListener { openDatePicker() }
        binding?.btnUploadFotoPegawai?.setOnClickListener { startGallery() }
        binding?.btnTambahPegawai?.setOnClickListener { tambahPegawai() }
    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Initial start and End Date
        selectedTanggalMulaiKerja = (day.toString() + "-" + (month + 1) + "-" + year)
        binding?.tvSelectedStartDate?.text = selectedTanggalMulaiKerja

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, monthOfYear, dayOfMonth ->
                selectedTanggalMulaiKerja = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + selectedYear)
                binding?.tvSelectedStartDate?.text = selectedTanggalMulaiKerja
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

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
            binding?.ivPreview?.setImageURI(it)
        }
    }

    private fun tambahPegawai() {

    }
}