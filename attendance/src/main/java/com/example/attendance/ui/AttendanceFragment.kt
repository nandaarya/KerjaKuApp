package com.example.attendance.ui

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
import com.example.attendance.databinding.FragmentAttendanceBinding
import com.example.attendance.utils.GeofenceBroadcastReceiver
import com.example.attendance.utils.addGeofence
import com.example.attendance.utils.startLocationUpdates
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext

@AndroidEntryPoint
class AttendanceFragment : Fragment() {

    private val attendanceViewModel: AttendanceViewModel by viewModels()

    private var _binding: FragmentAttendanceBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentAttendanceBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLocation(requireContext())
        registerGeofenceReceiver(requireContext())
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

    private fun registerGeofenceReceiver(context: Context) {
        LocalBroadcastManager.getInstance(context)
            .registerReceiver(
                geofenceEventReceiver,
                IntentFilter(GeofenceBroadcastReceiver.ACTION_GEOFENCE_EVENT)
            )
    }

    private fun unregisterGeofenceReceiver(context: Context) {
        LocalBroadcastManager.getInstance(context)
            .unregisterReceiver(geofenceEventReceiver)
    }

    override fun onDestroyView() {
        unregisterGeofenceReceiver(requireContext())
        super.onDestroyView()
        _binding = null
    }
}