package com.example.kerjakuapp.ui.main

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.NavHostFragment
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
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
                    val action =
                        NavDeepLinkRequest.Builder.fromUri("app://com.example.attendance.ui.attendance.AttendanceFragment".toUri())
                            .build()
                    navController.popBackStack()
                    navController.navigate(action)
                    return@setOnItemSelectedListener true
                }

                R.id.servicesFragment -> {
                    // variable role mengobrserve di view model untuk mendapatkan role user
                    val role =
                        "admin" // Replace with your method to get user role ("admin" or "employee")
                    val destinationUri = when (role) {
                        "admin" -> "app://com.example.services_admin.ui.servicesadmin.ServicesAdminFragment".toUri()
                        "employee" -> "app://com.example.services_employee.ui.servicesemployee.ServicesEmployeeFragment".toUri()
                        else -> null
                    }

                    if (destinationUri != null) {
                        val action = NavDeepLinkRequest.Builder.fromUri(destinationUri).build()
                        navController.popBackStack()
                        navController.navigate(action)
                    }
                    return@setOnItemSelectedListener true
                }

                R.id.profileFragment -> {
                    val action =
                        NavDeepLinkRequest.Builder.fromUri("app://com.example.profile.ui.profile.ProfileFragment".toUri())
                            .build()
                    navController.popBackStack()
                    navController.navigate(action)
                    return@setOnItemSelectedListener true
                }

                else -> return@setOnItemSelectedListener false
            }
        }

        navView?.setOnItemReselectedListener { item ->
            val message = when (item.itemId) {
                R.id.attendanceFragment -> "You're already on the Home page"
                R.id.servicesFragment -> "You're already on the Services page"
                R.id.profileFragment -> "You're already on the Profile page"
                else -> return@setOnItemReselectedListener
            }
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
//                R.id.splashscreenFragment, R.id.loginFragment -> {
//                    navView?.visibility = View.GONE
//                    supportActionBar?.hide()
//                }

                // later i will remove this ui update to the fragment themself
                com.example.attendance.R.id.attendanceFragment -> {
                    navView?.visibility = View.VISIBLE
                    supportActionBar?.show()

                    supportActionBar?.apply {
                        title = getString(R.string.app_name)
                        setDisplayHomeAsUpEnabled(false)
                    }
                }

                com.example.services_admin.R.id.servicesAdminFragment -> {
                    navView?.visibility = View.VISIBLE
                    supportActionBar?.show()

                    supportActionBar?.apply {
                        title = getString(R.string.title_services_admin)
                        setDisplayHomeAsUpEnabled(false)
                    }
                }

                // menu services_admin
                com.example.services_admin.R.id.addEmployeeFragment,
                com.example.services_admin.R.id.employeeLeaveReviewFragment,
                com.example.services_admin.R.id.employeeSickLeaveFragment,
                com.example.services_admin.R.id.reimbursementReviewFragment -> {
                    navView?.visibility = View.GONE
                }

                com.example.services_employee.R.id.servicesEmployeeFragment -> {
                    navView?.visibility = View.VISIBLE
                    supportActionBar?.show()

                    supportActionBar?.apply {
                        title = getString(R.string.title_services_employee)
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