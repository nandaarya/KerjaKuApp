package com.example.kerjakuapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kerjakuapp.ui.main.MainActivity
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == ACTION_GEOFENCE_EVENT) {
            val geofencingEvent = GeofencingEvent.fromIntent(intent) ?: return

            if (geofencingEvent.hasError()) {
                val errorMessage = GeofenceStatusCodes.getStatusCodeString(geofencingEvent.errorCode)
                Log.e(TAG, errorMessage)
                sendNotification(context, errorMessage)
                return
            }

            val geofenceTransitionString =
                when (geofencingEvent.geofenceTransition) {
                    Geofence.GEOFENCE_TRANSITION_ENTER -> GEOFENCE_TRANSITION_ENTER
                    Geofence.GEOFENCE_TRANSITION_DWELL -> GEOFENCE_TRANSITION_DWELL
                    Geofence.GEOFENCE_TRANSITION_EXIT -> GEOFENCE_TRANSITION_EXIT
                    else -> "Invalid transition type"
                }

            val triggeringGeofences = geofencingEvent.triggeringGeofences
            triggeringGeofences?.forEach { _ ->
                Log.i(TAG, geofenceTransitionString)
                sendNotification(context, geofenceTransitionString)
            }
        }
    }

    private fun sendNotification(context: Context, geofenceTransitionDetails: String) {
        val intentNotification = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intentNotification,
            PendingIntent.FLAG_IMMUTABLE
        )

        val mNotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(geofenceTransitionDetails)
            .setContentText("Anda sudah bisa absen sekarang :)")
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            mBuilder.setChannelId(CHANNEL_ID)
            mNotificationManager.createNotificationChannel(channel)
        }
        val notification = mBuilder.build()
        mNotificationManager.notify(NOTIFICATION_ID, notification)

        val intent = Intent(ACTION_GEOFENCE_EVENT)
        intent.putExtra(EXTRA_GEOFENCE_TRANSITION, geofenceTransitionDetails)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }

    companion object {
        private const val TAG = "GeofenceBroadcast"
        const val ACTION_GEOFENCE_EVENT = "GeofenceEvent"
        const val EXTRA_GEOFENCE_TRANSITION = "GeofenceTransition"
        private const val CHANNEL_ID = "1"
        private const val CHANNEL_NAME = "Geofence Channel"
        private const val NOTIFICATION_ID = 1
        const val GEOFENCE_TRANSITION_ENTER = "Anda telah memasuki area kantor"
        const val GEOFENCE_TRANSITION_DWELL = "Anda telah di dalam area kantor"
        const val GEOFENCE_TRANSITION_EXIT = "Anda telah keluar area kantor"
    }
}