package com.example.kerjakuapp.ui.home.ui.home

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.kerjakuapp.GeofenceBroadcastReceiver
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.FragmentHomeBinding
import com.example.kerjakuapp.ui.clockin.ClockInActivity
import com.example.kerjakuapp.ui.home.ui.services.ServicesFragment
import com.example.kerjakuapp.ui.reimbursement.ReimbursementActivity
import com.example.kerjakuapp.utils.ViewModelFactory
import com.example.kerjakuapp.utils.getCurrentDate
import com.example.kerjakuapp.utils.getCurrentDayOfWeek
import com.example.kerjakuapp.utils.getCurrentTime
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import java.util.Timer
import java.util.TimerTask

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mLastLocation: Location
    private val interval: Long = 10000 // 10seconds
    private val fastestInterval: Long = 5000 // 5 seconds
    private val requestPermissionCode = 999

    //koordinat kos (-7.778558, 110.399779)
    private val centerLat = -7.773271
    private val centerLng = 110.401591
    private val geofenceRadius = 50 // meter

    private lateinit var geofencingClient: GeofencingClient

    private lateinit var currentDayOfWeek: String
    private lateinit var currentDate: String
    private lateinit var currentTime: String

    private val geofenceEventReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == GeofenceBroadcastReceiver.ACTION_GEOFENCE_EVENT) {
                // Geofence event received, update the button or perform any action
                val geofenceTransition =
                    intent.getStringExtra(GeofenceBroadcastReceiver.EXTRA_GEOFENCE_TRANSITION)
                Log.i("Geofence Transition", geofenceTransition.toString())
                if (_binding != null) {
                    binding.btnClockIn.isEnabled =
                        geofenceTransition == GeofenceBroadcastReceiver.GEOFENCE_TRANSITION_ENTER || geofenceTransition == GeofenceBroadcastReceiver.GEOFENCE_TRANSITION_DWELL
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireContext())
        val homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        mLocationRequest = LocationRequest.create()

        // Register the broadcast receiver
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            geofenceEventReceiver, IntentFilter(GeofenceBroadcastReceiver.ACTION_GEOFENCE_EVENT)
        )

        checkGPSIsEnabled()

        startLocationUpdates()

        currentDayOfWeek = getCurrentDayOfWeek()
        currentDate = getCurrentDate()

        val currentDayDate = getString(R.string.day_date_format, currentDayOfWeek, currentDate)
        binding.tvDayDate.text = currentDayDate

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                currentTime = getCurrentTime()

                if (_binding != null) {
                    requireActivity().runOnUiThread {
                        binding.tvClock.text = currentTime
                    }
                }
            }
        }, 0, 60000)

        binding.cvIdentityLayout.setOnClickListener {
//            findNavController().navigate(R.id.navigation_profile)
        }

        binding.btnClockIn.setOnClickListener {
            val intent = Intent(requireContext(), ClockInActivity::class.java)
            intent.putExtra(EXTRA_NAME, "Nanda Arya Putra")
            intent.putExtra(EXTRA_ID, "21106050048")
            intent.putExtra(EXTRA_CURRENT_DAY_DATE, currentDayDate)
            intent.putExtra(EXTRA_CURRENT_TIME, currentTime)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        // Unregister the broadcast receiver to avoid memory leaks
        LocalBroadcastManager.getInstance(requireContext())
            .unregisterReceiver(geofenceEventReceiver)
        super.onDestroyView()
        _binding = null
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation
            locationResult.lastLocation?.let { locationChanged(it) }
        }
    }

    private fun startLocationUpdates() {
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = interval
        mLocationRequest.fastestInterval = fastestInterval

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        val locationSettingsRequest = builder.build()
        val settingsClient = LocationServices.getSettingsClient(requireContext())
        settingsClient.checkLocationSettings(locationSettingsRequest)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())

        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationProviderClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback, Looper.myLooper()!!
        )
    }

    private fun checkGPSIsEnabled() {
        val locationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("The location permission is disabled. Do you want to enable it?")
                .setCancelable(true)
                .setPositiveButton("Yes") { _, _ ->
                    startActivityForResult(
                        Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 10
                    )
                }.setNegativeButton("No") { dialog, _ ->
                    dialog.cancel()
                }
            val alert: AlertDialog = builder.create()
            alert.show()
        }
    }

    fun locationChanged(location: Location) {
        mLastLocation = location
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_CURRENT_DAY_DATE = "extra_current_day_date"
        const val EXTRA_CURRENT_TIME = "extra_current_time"
    }
}
