package com.example.services_admin.ui.servicesadmin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.services_admin.databinding.FragmentServicesAdminBinding

class ServicesAdminFragment : Fragment() {

    private var _binding: FragmentServicesAdminBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentServicesAdminBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButton()
    }

    private fun setupButton() {
        binding?.apply {
            btnTambahPegawai.setOnClickListener {
                val action =
                    ServicesAdminFragmentDirections.actionServicesAdminFragmentToAddEmployeeFragment()
                findNavController().navigate(action)
            }

            // Button for Review Pengajuan Cuti with Safe Args
            btnReviewPengajuanCuti.setOnClickListener {
//                val action = ServicesAdminFragmentDirections.actionServicesAdminFragmentToReviewPengajuanCutiFragment()
//                findNavController().navigate(action)
            }

            // Button for Izin Sakit Pegawai with Safe Args (assuming fragment exists)
            btnIzinSakitPegawai.setOnClickListener {
//                val action = ServicesAdminFragmentDirections.actionServicesAdminFragmentToIzinSakitPegawaiFragment()
//                findNavController().navigate(action)
            }

            // Button for Review Reimbursement with Safe Args (assuming fragment exists)
            btnReviewReimbursement.setOnClickListener {
//                val action = ServicesAdminFragmentDirections.actionServicesAdminFragmentToReviewReimbursementFragment()
//                findNavController().navigate(action)
            }

            btnEmployeeSalary.setOnClickListener {
//                val action = ServicesAdminFragmentDirections.actionServicesAdminFragmentToReviewReimbursementFragment()
//                findNavController().navigate(action)
            }
        }
    }

}