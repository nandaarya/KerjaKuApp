package com.example.kerjakuapp.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.ActivityMainBinding
import com.example.kerjakuapp.utils.checkGPSIsEnabled
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navHostFragment.navController
    }

    // Still find a way to separate the permission request
    private val requestNotificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Notifications permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Notifications permission rejected", Toast.LENGTH_SHORT).show()
        }
    }

    private val requestBackgroundLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Background permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Background Location Permission Denied", Toast.LENGTH_SHORT)
                    .show()
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

    private val requestLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    requestBackgroundLocationPermissionLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                }
            } else {
                Toast.makeText(this, "Izinkan Aplikasi Mengakses Lokasi", Toast.LENGTH_SHORT).show()
            }
        }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun checkForPermission(context: Context) {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )

        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                requestBackgroundLocationPermissionLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            }
        } else {
            requestLocationPermissionLauncher.launch(permissions)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        checkGPSIsEnabled(this@MainActivity)
        checkForPermission(this@MainActivity)
//        PermissionUtil.initialize(this@MainActivity)

        val navView: BottomNavigationView? = binding?.bottomNavigation
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashscreenFragment, R.id.loginFragment -> {
                    navView?.visibility = View.GONE
                    supportActionBar?.hide()

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        window.insetsController?.hide(WindowInsets.Type.statusBars())
                    } else {
                        window.setFlags(
                            WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN
                        )
                    }
                }
//                R.id.favoriteFragment -> {
//                    navView?.visibility = View.VISIBLE
//                    supportActionBar?.apply {
//                        title = getString(R.string.favorite_quote)
//                        setDisplayHomeAsUpEnabled(false)
//                    }
//                }
                else -> {
                    navView?.visibility = View.VISIBLE
                    supportActionBar?.show()
                    supportActionBar?.apply {
                        title = getString(R.string.app_name)
                        setDisplayHomeAsUpEnabled(false)
                    }
                }
            }
        }

        binding?.bottomNavigation?.setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_CURRENT_DAY_DATE = "extra_current_day_date"
        const val EXTRA_CURRENT_TIME = "extra_current_time"
    }
}