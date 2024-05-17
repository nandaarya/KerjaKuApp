package com.example.services_employee.ui.reimbursementapplication

import android.app.DatePickerDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ScrollView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.core.utils.getImageUri
import com.example.services_employee.databinding.FragmentReimbursementApplicationBinding
import java.util.Calendar

class ReimbursementApplicationFragment : Fragment() {

    private var _binding: FragmentReimbursementApplicationBinding? = null
    private val binding get() = _binding

    private var currentImageUri: Uri? = null
    private var selectedDate: String = ""

    private val calendar: Calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ScrollView? {
        _binding = FragmentReimbursementApplicationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext() as AppCompatActivity).supportActionBar?.apply {
            title = "Reimbursement"
            setDisplayHomeAsUpEnabled(true)
        }

        setupView()
        setupButton()
    }

    private fun setupView() {
        // Set jenis pengeluaran
        val jenisPengeluaranList = listOf("Transportasi", "Makanan", "Akomodasi", "Lain-lain")
        binding?.spinnerJenisPengeluaran?.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, jenisPengeluaranList)

        selectedDate = (day.toString() + "-" + (month + 1) + "-" + year)
        binding?.tvSelectedDate?.text = selectedDate
    }

    private fun setupButton() {
        binding?.btnDatePicker?.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    selectedDate = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding?.tvSelectedDate?.text = selectedDate
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        binding?.btnSimpan?.setOnClickListener {
            val jenisPengeluaran = binding?.spinnerJenisPengeluaran?.selectedItem.toString()
            val nominalPengeluaran = binding?.edtNominalPengeluaran?.text.toString().toInt()
            val keterangan = binding?.edtKeterangan?.text.toString()

            // Simpan data reimbursement
            Log.d(
                "button",
                "Ajukan Reimbursement. Data: $jenisPengeluaran, $nominalPengeluaran, $keterangan, $selectedDate"
            )
        }

        binding?.btnUploadBukti?.setOnClickListener { startCamera() }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(requireContext())
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
            binding?.ivPreviewBukti?.setImageURI(it)
        }
    }
}