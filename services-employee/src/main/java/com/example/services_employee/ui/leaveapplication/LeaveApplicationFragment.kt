package com.example.services_employee.ui.leaveapplication

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
import com.example.services_employee.databinding.FragmentLeaveApplicationBinding
import java.util.Calendar

class LeaveApplicationFragment : Fragment() {

    private var _binding: FragmentLeaveApplicationBinding? = null
    private val binding get() = _binding

    private var currentImageUri: Uri? = null
    private var selectedStartDate: String = ""
    private var selectedEndDate: String = ""

    private val calendar: Calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ScrollView? {
        _binding = FragmentLeaveApplicationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext() as AppCompatActivity).supportActionBar?.apply {
            title = "Pengajuan Cuti"
            setDisplayHomeAsUpEnabled(true)
        }

        setupView()
        setupButton()
    }

    private fun setupView() {
        val jenisCutiList = listOf(
            "Cuti untuk ambil Hak Cuti",
            "Cuti karena alasan penting",
            "Cuti melahirkan",
            "Cuti menikah",
            "Cuti untuk keperluan dinas"
        )
        binding?.spinnerJenisCuti?.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, jenisCutiList)

        // Initial start and End Date
        selectedStartDate = (day.toString() + "-" + (month + 1) + "-" + year)
        selectedEndDate = (day.toString() + "-" + (month + 1) + "-" + year)
        binding?.tvSelectedStartDate?.text = selectedStartDate
        binding?.tvSelectedEndDate?.text = selectedEndDate
    }

    private fun setupButton() {
        binding?.btnStartDatePicker?.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    selectedStartDate = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding?.tvSelectedStartDate?.text = selectedStartDate
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        binding?.btnEndDatePicker?.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    selectedEndDate = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding?.tvSelectedEndDate?.text = selectedEndDate
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        binding?.btnUploadBukti?.setOnClickListener { startCamera() }

        binding?.btnAjukanCuti?.setOnClickListener {
            val jenisCuti = binding?.spinnerJenisCuti?.selectedItem.toString()
            val keterangan = binding?.edtKeteranganCuti?.text.toString()

            // Simpan data ajukan cuti
            Log.d(
                "button",
                "Ajukan Cuti. Data: $jenisCuti, $selectedStartDate, $selectedEndDate, $keterangan"
            )
        }
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