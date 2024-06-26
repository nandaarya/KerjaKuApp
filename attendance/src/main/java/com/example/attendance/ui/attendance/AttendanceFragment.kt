package com.example.attendance.ui.attendance

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.example.attendance.R
import com.example.attendance.databinding.FragmentAttendanceBinding
import com.example.attendance.ui.clockout.ClockOutDialogFragment
import com.example.attendance.utils.GeofenceBroadcastReceiver
import com.example.attendance.utils.addGeofence
import com.example.attendance.utils.checkGPSIsEnabled
import com.example.attendance.utils.getCurrentDate
import com.example.attendance.utils.getCurrentDayOfWeek
import com.example.attendance.utils.getCurrentTime
import com.example.core.data.remote.network.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask

@AndroidEntryPoint
class AttendanceFragment : Fragment(), ClockOutDialogFragment.ClockOutListener {

    private val attendanceViewModel: AttendanceViewModel by viewModels()

    private var _binding: FragmentAttendanceBinding? = null

    private val binding get() = _binding

    private lateinit var currentDayOfWeek: String
    private lateinit var currentDate: String
    private lateinit var currentTime: String
    private lateinit var currentDayDate: String

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentAttendanceBinding.inflate(inflater, container, false)
        checkGPSIsEnabled(requireActivity())
        checkForPermission(requireContext())
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLocation(requireContext())
        registerGeofenceReceiver(requireContext())

        setupView()
        setupButton()
        setupTime()
    }

    private fun setupView() {
        attendanceViewModel.getSession().observe(requireActivity()){
            binding?.apply {
//                ivProfilePhoto.id =
                tvName.text = it.name
                tvId.text = it.userId
            }
        }

//        attendanceViewModel.getDataAttendance(employeeId = ).observe(requireActivity()){
//            binding?.apply {
//
//            }
//        }
    }

    // Still find a way to separate the permission request
    private val requestNotificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(requireContext(), "Notifications permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Notifications permission rejected", Toast.LENGTH_SHORT).show()
        }
    }

    private val requestBackgroundLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Background permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Background Location Permission Denied", Toast.LENGTH_SHORT)
                    .show()
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

    private val requestLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    requestBackgroundLocationPermissionLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                }
            } else {
                Toast.makeText(requireContext(), "Izinkan Aplikasi Mengakses Lokasi", Toast.LENGTH_SHORT).show()
            }
        }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun checkForPermission(context: Context) {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )

        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                requestBackgroundLocationPermissionLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            }
        } else {
            requestLocationPermissionLauncher.launch(permissions)
        }
    }

    private val geofenceEventReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == GeofenceBroadcastReceiver.ACTION_GEOFENCE_EVENT) {
                // Geofence event received, update the button or perform any action
                val geofenceTransition =
                    intent.getStringExtra(GeofenceBroadcastReceiver.EXTRA_GEOFENCE_TRANSITION)
                Log.d("Geofence Transition", geofenceTransition.toString())
                if (_binding != null) {
                    binding?.btnClockIn?.isEnabled =
                        geofenceTransition == GeofenceBroadcastReceiver.GEOFENCE_TRANSITION_ENTER || geofenceTransition == GeofenceBroadcastReceiver.GEOFENCE_TRANSITION_DWELL
                }
            }
        }
    }

    private fun setupLocation(context: Context) {
        addGeofence(context)
    }

    private fun setupTime() {
        currentDayOfWeek = getCurrentDayOfWeek()
        currentDate = getCurrentDate()

        currentDayDate = getString(R.string.day_date_format, currentDayOfWeek, currentDate)
        binding?.tvDayDate?.text = currentDayDate

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                currentTime = getCurrentTime()

                if (_binding != null) {
                    requireActivity().runOnUiThread {
                        binding?.tvClock?.text = currentTime
                    }
                }
            }
        }, 0, 60000)
    }

    private fun setupButton() {
        binding?.btnClockIn?.setOnClickListener {
            val action = AttendanceFragmentDirections.actionAttendanceFragmentToClockInFragment()
                .setName("Nanda Arya Putra")
                .setId("21106050048")
                .setCurrentDayDate(currentDayDate)
                .setCurrentTime(currentTime)
            findNavController().navigate(action)
        }

        // for test
        binding?.btnClockOut?.isEnabled = true
        binding?.btnClockOut?.setOnClickListener {
            val dialog = ClockOutDialogFragment(this) // Pass 'this' as the listener
            dialog.show(childFragmentManager, "clockOutDialog")
        }
    }

    private fun registerGeofenceReceiver(context: Context) {
        LocalBroadcastManager.getInstance(context).registerReceiver(
            geofenceEventReceiver, IntentFilter(GeofenceBroadcastReceiver.ACTION_GEOFENCE_EVENT)
        )
    }

    private fun unregisterGeofenceReceiver(context: Context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(geofenceEventReceiver)
    }

    override fun onDestroyView() {
        unregisterGeofenceReceiver(requireContext())
        super.onDestroyView()
        _binding = null
    }

    override fun onClockOutConfirmed() {
        attendanceViewModel.clockOut("employeeId", "date").observe(requireActivity()){
            when (it) {
                is ApiResponse.Loading -> {
                }
                is ApiResponse.Success -> {
                    Toast.makeText(requireContext(), "Clock out success!", Toast.LENGTH_SHORT).show()
                }
                is ApiResponse.Error -> {
                    Toast.makeText(requireContext(), "Clock out fail!", Toast.LENGTH_SHORT).show()
                }
                is ApiResponse.Empty -> {
                }
            }
        }
    }
}