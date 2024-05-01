package com.example.kerjakuapp.ui.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.ActivitySignupBinding
//import com.example.kerjakuapp.utils.ViewModelFactory
import com.example.kerjakuapp.data.Result
import com.example.kerjakuapp.ui.login.LoginActivity
//import org.koin.androidx.viewmodel.ext.android.viewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
//    private val signupViewModel: SignupViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
//        signupViewModel = ViewModelProvider(this, factory)[SignupViewModel::class.java]

//        signupViewModel.registerResponse.observe(this) {
//            when (it) {
//                is Result.Loading -> {
//                    showLoading(true)
//                }
//                is Result.Success -> {
//                    showLoading(false)
//                    AlertDialog.Builder(this).apply {
//                        setTitle("Yeah!")
//                        setMessage(getString(R.string.register_dialog_message))
//                        setCancelable(false)
//                        setPositiveButton(getString(R.string.dialog_positive_button)) { _, _ ->
//                            val intent = Intent(context, LoginActivity::class.java)
//                            startActivity(intent)
//                            finish()
//                        }
//                        create()
//                        show()
//                    }
//                }
//                is Result.Error -> {
//                    registerFailedToast()
//                    showLoading(false)
//                }
//            }
//        }

        setupView()
        setupAction()
        playAnimation()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnSignup.setOnClickListener {
            binding.apply {
                if (etName.error.isNullOrEmpty() && etEmail.error.isNullOrEmpty() && etPassword.error.isNullOrEmpty()) {
                    val name = etName.text.toString().trim()
                    val email = etEmail.text.toString().trim()
                    val password = etPassword.text.toString().trim()
//                    signupViewModel.register(name, email, password)
                } else {
                    registerFailedToast()
                }
            }
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(100)
        val nameTextView =
            ObjectAnimator.ofFloat(binding.tvName, View.ALPHA, 1f).setDuration(100)
        val nameEditTextLayout =
            ObjectAnimator.ofFloat(binding.tilName, View.ALPHA, 1f).setDuration(100)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.tilEmail, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.tvPassword, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.tilPassword, View.ALPHA, 1f).setDuration(100)
        val signup = ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1f).setDuration(100)


        AnimatorSet().apply {
            playSequentially(
                title,
                nameTextView,
                nameEditTextLayout,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                signup
            )
            startDelay = 100
        }.start()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun registerFailedToast() {
        Toast.makeText(
            this,
            R.string.register_failed,
            Toast.LENGTH_SHORT
        ).show()
    }
}