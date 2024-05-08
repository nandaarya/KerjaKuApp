package com.example.attendance.ui.attendance

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.example.attendance.R
import com.example.attendance.databinding.FragmentAttendanceBinding
import com.example.attendance.utils.GeofenceBroadcastReceiver
import com.example.attendance.utils.addGeofence
import com.example.attendance.utils.getCurrentDate
import com.example.attendance.utils.getCurrentDayOfWeek
import com.example.attendance.utils.getCurrentTime
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask

@AndroidEntryPoint
class AttendanceFragment : Fragment() {

    private val attendanceViewModel: AttendanceViewModel by viewModels()

    private var _binding: FragmentAttendanceBinding? = null

    private val binding get() = _binding

    private lateinit var currentDayOfWeek: String
    private lateinit var currentDate: String
    private lateinit var currentTime: String
    private lateinit var currentDayDate: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentAttendanceBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLocation(requireContext())
        registerGeofenceReceiver(requireContext())
        setupButton()
        setupTime()
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
}