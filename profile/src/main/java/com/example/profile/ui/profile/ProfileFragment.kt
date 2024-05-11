package com.example.profile.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.profile.databinding.FragmentProfileBinding
import com.example.profile.ui.logout.LogoutDialogFragment

class ProfileFragment : Fragment(), LogoutDialogFragment.LogoutListener {

    private val profileViewModel: ProfileViewModel by viewModels()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupButton()
    }

    private fun setupView() {

    }

    private fun setupButton() {
        binding?.btnLogout?.setOnClickListener {
            val dialog = LogoutDialogFragment(this) // Pass 'this' as the listener
            dialog.show(childFragmentManager, "clockOutDialog")
        }
    }

    override fun onLogoutConfirmed() {
        Toast.makeText(requireContext(), "Logout success!", Toast.LENGTH_SHORT).show()
    }
}