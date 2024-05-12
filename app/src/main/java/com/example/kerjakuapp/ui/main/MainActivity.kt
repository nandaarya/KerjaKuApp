package com.example.kerjakuapp.ui.main

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.ActivityMainBinding
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

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navView: BottomNavigationView? = binding?.bottomNavigation
        navView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.attendanceFragment -> {
                    val action = NavDeepLinkRequest.Builder
                        .fromUri("app://com.example.attendance.ui.attendance.AttendanceFragment".toUri())
                        .build()
                    navController.popBackStack()
                    navController.navigate(action)
                    return@setOnItemSelectedListener true
                }
                R.id.servicesFragment -> {
//                    val servicesFragment = ServicesFragment()  // Assuming ServicesFragment exists
//                    fragmentManager.replace(R.id.fragment_container, servicesFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.profileFragment -> {
                    val action = NavDeepLinkRequest.Builder
                        .fromUri("app://com.example.profile.ui.profile.ProfileFragment".toUri())
                        .build()
                    navController.popBackStack()
                    navController.navigate(action)
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashscreenFragment, R.id.loginFragment -> {
                    navView?.visibility = View.GONE
                    supportActionBar?.hide()
                }

                com.example.attendance.R.id.attendanceFragment -> {
                    navView?.visibility = View.VISIBLE
                    supportActionBar?.show()
                    supportActionBar?.apply {
                        title = getString(R.string.app_name)
                        setDisplayHomeAsUpEnabled(false)
                    }
                }

                com.example.profile.R.id.profileFragment -> {
                    navView?.visibility = View.VISIBLE
                    supportActionBar?.show()
                    supportActionBar?.apply {
                        title = getString(R.string.title_profile)
                        setDisplayHomeAsUpEnabled(false)
                    }
                }

                com.example.attendance.R.id.clockInFragment -> {
                    navView?.visibility = View.GONE
                    supportActionBar?.apply {
                        title = getString(com.example.attendance.R.string.clock_in)
                        setDisplayHomeAsUpEnabled(true)
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
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