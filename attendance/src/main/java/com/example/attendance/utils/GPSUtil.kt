package com.example.attendance.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog

fun checkGPSIsEnabled(context: Context) {
    Log.d("location", "Check GPS")
    val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("The location permission is disabled. Do you want to enable it?")
            .setCancelable(true)
            .setPositiveButton("Yes") { _, _ ->
                (context as Activity).startActivityForResult(
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 10
                )
            }.setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
        val alert: AlertDialog = builder.create()
        alert.show()
    }
}