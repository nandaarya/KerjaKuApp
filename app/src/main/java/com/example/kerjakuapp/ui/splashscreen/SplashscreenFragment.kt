package com.example.kerjakuapp.ui.splashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.FragmentSplashscreenBinding

@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashscreenFragment : Fragment() {

    private val splashTimeOut: Long = 2000
    private val handler = Handler()

    private var _binding: FragmentSplashscreenBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): LinearLayout? {
        _binding = FragmentSplashscreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        scheduleNavigation()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(navigationRunnable)
    }

    private val navigationRunnable = Runnable {
        val navController = findNavController()
        if (navController.currentDestination?.id != R.id.loginFragment) {
            navController.navigate(R.id.action_splashscreenFragment_to_loginFragment)
        }
    }

    private fun scheduleNavigation() {
        handler.postDelayed(navigationRunnable, splashTimeOut)
    }
}