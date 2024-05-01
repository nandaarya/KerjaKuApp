package com.example.kerjakuapp.utils

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

object PermissionUtil {

//    private lateinit var activity: AppCompatActivity
//
//    fun initialize(activity: AppCompatActivity) {
//        PermissionUtil.activity = activity
//
//        checkLocationPermission {
//            checkNotificationPermission {
//                checkBackgroundLocationPermission {
//
//                }
//            }
//        }
//    }
//
//    fun checkLocationPermission(onPermissionGranted: () -> Unit) {
//        Log.d("Permission", "Location Permission")
//        val permissions = arrayOf(
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.ACCESS_COARSE_LOCATION
//        )
//
//        if (permissions.all {
//                ContextCompat.checkSelfPermission(
//                    activity,
//                    it
//                ) == PackageManager.PERMISSION_GRANTED
//            }
//        ) {
//            // Permissions are already granted.
//            Toast.makeText(
//                activity,
//                "Location permission already granted",
//                Toast.LENGTH_SHORT
//            ).show()
//            onPermissionGranted()
//        } else {
//            // Request permissions.
//            activity.registerForActivityResult(
//                ActivityResultContracts.RequestMultiplePermissions()
//            ) { permissions ->
//                if (permissions.all { it.value }) {
//                    // All permissions are granted.
//                    onPermissionGranted()
//                } else {
//                    Toast.makeText(
//                        activity,
//                        "Izinkan Aplikasi Mengakses Lokasi",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }.launch(permissions)
//        }
//    }
//
//    fun checkNotificationPermission(onPermissionGranted: () -> Unit) {
//        val permission = Manifest.permission.POST_NOTIFICATIONS
//
//        if (ContextCompat.checkSelfPermission(
//                activity,
//                permission
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            // Notification permission is already granted.
//            Toast.makeText(
//                activity,
//                "Notification permission already granted",
//                Toast.LENGTH_SHORT
//            ).show()
//
//            onPermissionGranted()
//        } else {
//            // Request notification permission.
//            activity.registerForActivityResult(
//                ActivityResultContracts.RequestPermission()
//            ) { isGranted: Boolean ->
//                if (isGranted) {
//                    onPermissionGranted()
//                    Toast.makeText(
//                        activity,
//                        "Notifications permission granted",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                } else {
//                    Toast.makeText(
//                        activity,
//                        "Notifications permission rejected",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }.launch(permission)
//        }
//    }
//
//    fun checkBackgroundLocationPermission(onPermissionGranted: () -> Unit) {
//        val permission = Manifest.permission.ACCESS_BACKGROUND_LOCATION
//
//        if (ContextCompat.checkSelfPermission(
//                activity,
//                permission
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            // Background location permission is already granted.
//            Toast.makeText(
//                activity,
//                "Background permission already granted",
//                Toast.LENGTH_SHORT
//            ).show()
//            onPermissionGranted()
//        } else {
//            // Request background location permission.
//            activity.registerForActivityResult(
//                ActivityResultContracts.RequestPermission()
//            ) { isGranted: Boolean ->
//                if (isGranted) {
//                    onPermissionGranted()
//                    Toast.makeText(
//                        activity,
//                        "Background permission granted",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                } else {
//                    Toast.makeText(
//                        activity,
//                        "Background Location Permission Denied",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }.launch(permission)
//        }
//    }

}