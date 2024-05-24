package com.example.profile.ui.profile

import android.content.Intent
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        profileViewModel.getSession().observe(requireActivity()){
            binding?.apply {
//                ivProfilePhoto.id =
                tvName.text = it.name
                tvId.text = it.userId
            }
        }
    }

    private fun setupButton() {
        binding?.btnLogout?.setOnClickListener {
            val dialog = LogoutDialogFragment(this) // Pass 'this' as the listener
            dialog.show(childFragmentManager, "clockOutDialog")
        }
    }

    override fun onLogoutConfirmed() {
        profileViewModel.logout()
        Toast.makeText(requireContext(), "Logout success!", Toast.LENGTH_SHORT).show()

        val intent = Intent(Intent.ACTION_MAIN)
        intent.setClassName(requireContext(), "com.example.kerjakuapp.ui.main.MainActivity")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }
}