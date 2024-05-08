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
import androidx.navigation.fragment.navArgs
import com.example.attendance.R
import com.example.attendance.databinding.FragmentClockInBinding
import com.example.attendance.utils.getImageUri
import com.example.attendance.utils.isTimeInRange
import com.example.attendance.utils.parseTimeStringToTime
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClockInFragment : Fragment() {

//    private val clockInViewModel: ClockInViewModel by viewModels()

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
            // Memeriksa apakah waktu berada dalam rentang
            if (isTimeInRange(currentTime, startTime, endTime)) {
                val toast =
                    Toast.makeText(requireContext(), "Anda Telah Clock In", Toast.LENGTH_LONG)
                toast.show()
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

    //    private fun uploadImage() {
//        var token: String
//        currentImageUri?.let { uri ->
//            val imageFile = uriToFile(uri, this).reduceFileImage()
//            val description = binding.edtDescription.text.toString()
//            showLoading(true)
//
//            val requestBody = description.toRequestBody("text/plain".toMediaType())
//            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
//            val multipartBody = MultipartBody.Part.createFormData(
//                "photo",
//                imageFile.name,
//                requestImageFile
//            )
//
//            uploadStoryViewModel.getSession().observe(this) { user ->
//                token = user.token
//                uploadStoryViewModel.uploadStory(token, multipartBody, requestBody, currentLocation)
//            }
//
//        } ?: showToast(getString(com.google.android.gms.location.R.string.empty_image_warning))
//    }
}