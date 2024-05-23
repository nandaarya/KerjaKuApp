package com.example.kerjakuapp.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.data.remote.network.ApiResponse
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding

    // Variabel test
//    private var userRole = "employee"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkIfLoggedIn()

        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility =
            View.GONE
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        setupAction()
        playAnimation()
    }

    private fun checkIfLoggedIn() {
        loginViewModel.getSession().observe(requireActivity()){
            if (it.isLoggedIn) {
                findNavController().navigate(R.id.action_loginFragment_to_attendance_navigation)
            }
        }
    }

    private fun setupAction() {
        binding?.btnLogin?.setOnClickListener {
            binding?.apply {
                if (etEmail.error.isNullOrEmpty() && etPassword.error.isNullOrEmpty()) {
                    val email = etEmail.text.toString().trim()
                    val password = etPassword.text.toString().trim()
                    Log.d("login", "$email, $password")
                    loginResult(email, password)
                }
            }
        }

//        // TEST LOGIN DENGAN BERBAGAI USER
//        binding?.switchRole?.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                userRole = "admin"
//                Toast.makeText(requireContext(), "Login sebagai Admin", Toast.LENGTH_SHORT).show()
//            } else {
//                userRole = "employee"
//                Toast.makeText(requireContext(), "Login sebagai User Biasa", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
    }

    private fun loginResult(email: String, password: String) {
        loginViewModel.login(email, password).observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    showLoading(true)
                }
                is ApiResponse.Success -> {
                    showLoading(false)
//                    loginViewModel.saveSession(it.data)
                    Log.d("user after login", it.data.toString())
                    findNavController().navigate(R.id.action_loginFragment_to_attendance_navigation)
                }
                is ApiResponse.Error -> {
                    alertDialog(getString(R.string.login_failed_dialog_title), getString(R.string.login_failed_dialog))
                    showLoading(false)
                }
                is ApiResponse.Empty -> {
                    alertDialog(getString(R.string.login_failed_dialog_title), "Data Login Kosong!")
                    showLoading(false)
                }
            }
        }
    }

    private fun alertDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(title)
            setMessage(message)
            create()
            show()
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding?.ivLogo, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val emailTextView =
            ObjectAnimator.ofFloat(binding?.tvEmail, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding?.tilEmail, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding?.tvPassword, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding?.tilPassword, View.ALPHA, 1f).setDuration(100)
        val login = ObjectAnimator.ofFloat(binding?.btnLogin, View.ALPHA, 1f).setDuration(100)
        val copyrightTextView =
            ObjectAnimator.ofFloat(binding?.tvCopyright, View.ALPHA, 1f).setDuration(100)


        AnimatorSet().apply {
            playSequentially(
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login,
                copyrightTextView,
            )
            startDelay = 100
        }.start()
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}