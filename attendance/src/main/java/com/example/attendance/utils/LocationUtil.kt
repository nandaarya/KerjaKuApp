package com.example.attendance.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import dagger.hilt.android.qualifiers.ActivityContext

private lateinit var geofencingClient: GeofencingClient
// Koordinat Leha-leha
//    -7.792712, 110.405168
// Koordinat Kos
//  -7.779505081447887, 110.3910005479428
private val centerLat = -7.779505081447887
private val centerLng = 110.3910005479428
private val geofenceRadius = 500 //meter
//private val requestPermissionCode = 999
private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
private lateinit var mLocationRequest: LocationRequest
private lateinit var mLastLocation: Location

@SuppressLint("MissingPermission")
fun addGeofence(context: Context) {
    Log.d("location", "Add Geofence")
    geofencingClient = LocationServices.getGeofencingClient(context)

    val geofence = Geofence.Builder().setRequestId("kantor").setCircularRegion(
        centerLat, centerLng, geofenceRadius.toFloat()
    ).setExpirationDuration(Geofence.NEVER_EXPIRE)
        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_DWELL or Geofence.GEOFENCE_TRANSITION_ENTER)
        .setLoiteringDelay(5000).build()

    val geofencingRequest =
        GeofencingRequest.Builder().setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofence(geofence).build()

    val geofencePendingIntent: PendingIntent by lazy {
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
        intent.action = GeofenceBroadcastReceiver.ACTION_GEOFENCE_EVENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    geofencingClient.removeGeofences(geofencePendingIntent).run {
        addOnCompleteListener {
            Log.d("geofence", "pending intent dikirim atau tidak")
            geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent).run {
                addOnSuccessListener {
                    showToast(context, "Area Telah Terpasang")
                    startLocationUpdates(context)
                }
                addOnFailureListener {
                    showToast(context, "Area Belum Terpasang. Tunggu Sebentar.")
                    Handler().postDelayed({
                        addGeofence(context)
                    }, 10000)
                }
            }
        }
    }
}

fun startLocationUpdates(context: Context) {
    Log.d("Location", "start location update")
    val interval: Long = 1000 // 1seconds
    val fastestInterval: Long = 500 // 0.5 seconds

    mLocationRequest = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        this.interval = interval
        this.fastestInterval = fastestInterval
    }

    val builder = LocationSettingsRequest.Builder()
    builder.addLocationRequest(mLocationRequest)
    val locationSettingsRequest = builder.build()
    val settingsClient = LocationServices.getSettingsClient(context)
    settingsClient.checkLocationSettings(locationSettingsRequest)

    fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    if (ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        return
    }

    fusedLocationProviderClient.requestLocationUpdates(
        mLocationRequest, mLocationCallback, Looper.myLooper()!!
    )
}

private val mLocationCallback = object : LocationCallback() {
    override fun onLocationResult(locationResult: LocationResult) {
        locationResult.lastLocation
        locationResult.lastLocation?.let { locationChanged(it) }
    }
}

fun locationChanged(location: Location) {
    mLastLocation = location
}

private fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}