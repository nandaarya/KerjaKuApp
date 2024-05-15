package com.example.kerjakuapp.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        setupAction()
        playAnimation()
    }

    private fun setupAction() {
        binding?.btnLogin?.setOnClickListener {
            binding?.apply {
                if (etEmail.error.isNullOrEmpty() && etPassword.error.isNullOrEmpty()) {
                    val email = etEmail.text.toString().trim()
                    val password = etPassword.text.toString().trim()
                    //                    loginViewModel.login(email, password)

                }
            }
            // Login without Login API
            findNavController().navigate(R.id.action_loginFragment_to_attendance_navigation)
//            val intent = Intent(this, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(intent)
//            finish()
        }

        binding?.btnSignup?.setOnClickListener {
//            val intent = Intent(this, SignupActivity::class.java)
//            startActivity(intent)
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
        val signupTextView =
            ObjectAnimator.ofFloat(binding?.tvSignup, View.ALPHA, 1f).setDuration(100)
        val signup = ObjectAnimator.ofFloat(binding?.btnSignup, View.ALPHA, 1f).setDuration(100)
        val copyrightTextView =
            ObjectAnimator.ofFloat(binding?.tvCopyright, View.ALPHA, 1f).setDuration(100)


        AnimatorSet().apply {
            playSequentially(
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login,
                signupTextView,
                signup,
                copyrightTextView,
            )
            startDelay = 100
        }.start()
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}