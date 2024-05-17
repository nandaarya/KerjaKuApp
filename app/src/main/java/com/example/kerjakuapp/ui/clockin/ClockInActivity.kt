package com.example.kerjakuapp.ui.clockin

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.ActivityClockInBinding
import com.example.kerjakuapp.ui.main.MainActivity.Companion.EXTRA_CURRENT_DAY_DATE
import com.example.kerjakuapp.ui.main.MainActivity.Companion.EXTRA_CURRENT_TIME
import com.example.kerjakuapp.ui.main.MainActivity.Companion.EXTRA_ID
import com.example.kerjakuapp.ui.main.MainActivity.Companion.EXTRA_NAME
import com.example.core.utils.getImageUri
import com.example.kerjakuapp.utils.isTimeInRange
import com.example.kerjakuapp.utils.parseTimeStringToTime

class ClockInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClockInBinding
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Clock In"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra(EXTRA_NAME)
        val id = intent.getStringExtra(EXTRA_ID)
        val currentDayDate = intent.getStringExtra(EXTRA_CURRENT_DAY_DATE)
        val currentTimeString = intent.getStringExtra(EXTRA_CURRENT_TIME) ?: "00:00"

        binding.tvName.text = getString(R.string.name_placeholder, name)
        binding.tvId.text = getString(R.string.id_placeholder, id)
        binding.tvDayDate.text = getString(R.string.day_date_placeholder, currentDayDate)
        binding.tvTime.text = getString(R.string.time_placeholder, currentTimeString)

        // Menggunakan fungsi parseTimeStringToTime
        val startTimeString = "06:00"
        val endTimeString = "07:00"

        val startTime = parseTimeStringToTime(startTimeString)
        val endTime = parseTimeStringToTime(endTimeString)
        val currentTime = parseTimeStringToTime(currentTimeString)

        binding.btnCamera.setOnClickListener { startCamera() }
        binding.btnUpload.setOnClickListener {
            Log.d("Clock In", currentTime.toString())
            // Memeriksa apakah waktu berada dalam rentang
            if (isTimeInRange(currentTime, startTime, endTime)) {
                val toast = Toast.makeText(this, "Anda Telah Clock In", Toast.LENGTH_LONG)
                toast.show()
            } else {
                val toast = Toast.makeText(this, "Belum saatnya Clock In", Toast.LENGTH_LONG)
                toast.show()
            }
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
            binding.ivPreview.setImageURI(it)
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