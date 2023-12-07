package com.example.kerjakuapp.ui.home.ui.services

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kerjakuapp.databinding.FragmentServicesBinding
import com.example.kerjakuapp.ui.home.ui.profile.ProfileViewModel
import com.example.kerjakuapp.ui.reimbursement.ReimbursementActivity
import com.example.kerjakuapp.ui.signup.SignupActivity
import com.example.kerjakuapp.utils.ViewModelFactory

class ServicesFragment : Fragment() {

    private var _binding: FragmentServicesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireContext())
        val servicesViewModel = ViewModelProvider(this, factory)[ServicesViewModel::class.java]

        _binding = FragmentServicesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnIzinSakit.setOnClickListener{
            Log.d("button", "Izin Sakit")
        }

        binding.btnPengajuanCuti.setOnClickListener{
            Log.d("button", "Pengajuan Cuti")
        }

        binding.btnReimbursement.setOnClickListener{
            val intent = Intent(requireContext(), ReimbursementActivity::class.java)
            startActivity(intent)
            Log.d("button", "Reimbursement")
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}