package com.example.profile.ui.logout

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController

class LogoutDialogFragment(private val logoutListener: LogoutListener) :
    DialogFragment() {

    interface LogoutListener {
        fun onLogoutConfirmed()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
            .setMessage("Are you sure you want to Logout?")
            .setPositiveButton("Yes") { _, _ ->
                // delete data login
                logoutListener.onLogoutConfirmed()
                val action = NavDeepLinkRequest.Builder
                    .fromUri("app://com.example.kerjakuapp.ui.login.LoginFragment".toUri())
                    .build()
                findNavController().navigate(action)
            }
            .setNegativeButton("No") { _, _ ->
                // Do nothing if the user cancels
            }
        return builder.create()
    }
}