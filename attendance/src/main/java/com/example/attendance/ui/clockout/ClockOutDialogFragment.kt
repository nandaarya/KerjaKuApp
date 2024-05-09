package com.example.attendance.ui.clockout

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ClockOutDialogFragment(private val clockOutListener: ClockOutListener) : DialogFragment() {

    interface ClockOutListener {
        fun onClockOutConfirmed()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Clock Out")
            .setMessage("Are you sure you want to clock out?")
            .setPositiveButton("Yes") { _, _ ->
                clockOutListener.onClockOutConfirmed()
            }
            .setNegativeButton("No") { _, _ ->
                // Do nothing if the user cancels
            }
        return builder.create()
    }
}