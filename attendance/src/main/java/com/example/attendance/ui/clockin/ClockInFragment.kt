package com.example.attendance.ui.clockin

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.attendance.R
import com.example.attendance.databinding.FragmentClockInBinding
import com.example.attendance.utils.getImageUri
import com.example.attendance.utils.isTimeInRange
import com.example.attendance.utils.parseTimeStringToTime
import com.example.attendance.utils.reduceFileImage
import com.example.attendance.utils.uriToFile
import com.example.core.data.remote.network.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

@AndroidEntryPoint
class ClockInFragment : Fragment() {

    private val clockInViewModel: ClockInViewModel by viewModels()

    private var _binding: FragmentClockInBinding? = null

    private val binding get() = _binding
    private var currentImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentClockInBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        val args: ClockInFragmentArgs by navArgs()

        val name = args.name
        val id = args.id
        val currentDayDate = args.currentDayDate
        val currentTimeString = args.currentTime

        binding?.tvName?.text = getString(R.string.name_placeholder, name)
        binding?.tvId?.text = getString(R.string.id_placeholder, id)
        binding?.tvDayDate?.text = getString(R.string.day_date_placeholder, currentDayDate)
        binding?.tvTime?.text = getString(R.string.time_placeholder, currentTimeString)

        // Menggunakan fungsi parseTimeStringToTime
        val startTimeString = "06:00"
        val endTimeString = "07:00"

        val startTime = parseTimeStringToTime(startTimeString)
        val endTime = parseTimeStringToTime(endTimeString)
        val currentTime = parseTimeStringToTime(currentTimeString)

        binding?.btnCamera?.setOnClickListener { startCamera() }
        binding?.btnUpload?.setOnClickListener {
            Log.d("Clock In", currentTime.toString())
            if (isTimeInRange(currentTime, startTime, endTime)) {
//                val toast =
//                    Toast.makeText(requireContext(), "Anda Telah Clock In", Toast.LENGTH_LONG)
//                toast.show()
                // upload clock in
            } else {
                val toast =
                    Toast.makeText(requireContext(), "Belum saatnya Clock In", Toast.LENGTH_LONG)
                toast.show()
            }
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
            binding?.ivPreview?.setImageURI(it)
        }
    }

    private fun clockIn() {
        var token: String
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, requireContext()).reduceFileImage()
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val file = MultipartBody.Part.createFormData(
                "photo clock in",
                imageFile.name,
                requestImageFile
            )

            clockInViewModel.clockIn("employeeId", file).observe(requireActivity()){
                when (it) {
                    is ApiResponse.Loading -> {
                        showLoading(true)
                    }
                    is ApiResponse.Success -> {
                        showLoading(false)
                        Toast.makeText(requireContext(), "Clock in success!", Toast.LENGTH_SHORT).show()
                        val action = ClockInFragmentDirections.actionClockInFragmentToAttendanceFragment()
                        findNavController().navigate(action)
                    }
                    is ApiResponse.Error -> {
                        showLoading(false)
                        Toast.makeText(requireContext(), "Clock in fail!", Toast.LENGTH_SHORT).show()
                    }
                    is ApiResponse.Empty -> {
                        showLoading(false)
                        Toast.makeText(requireContext(), "Clock in fail! Empty Result!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}