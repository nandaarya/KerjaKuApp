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
import com.example.attendance.databinding.FragmentAttendanceBinding
import com.example.attendance.utils.GeofenceBroadcastReceiver
import com.example.attendance.utils.addGeofence
import dagger.hilt.android.AndroidEntryPoint

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

    private fun setupButton() {
        binding?.btnClockIn?.setOnClickListener {
//            val bundle = bundleOf(DetailFragment.QUOTE_DATA_KEY to quote)
//            findNavController().navigate(R.id., bundle)

            //        binding.btnClockIn.setOnClickListener {
//            val intent = Intent(requireContext(), ClockInActivity::class.java)
//            intent.putExtra(EXTRA_NAME, "Nanda Arya Putra")
//            intent.putExtra(EXTRA_ID, "21106050048")
//            intent.putExtra(EXTRA_CURRENT_DAY_DATE, currentDayDate)
//            intent.putExtra(EXTRA_CURRENT_TIME, currentTime)
//            startActivity(intent)
//        }
        }
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