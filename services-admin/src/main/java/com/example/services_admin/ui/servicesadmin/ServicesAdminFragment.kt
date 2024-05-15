package com.example.services_admin.ui.servicesadmin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
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
//            btnTambahPegawai.setOnClickListener {
//                val intent = Intent(requireContext(), TambahPegawaiActivity::class.java)
//                startActivity(intent)
//                Log.d("button", "Tambah Pegawai")
//            }
//
//            btnReviewPengajuanCuti.setOnClickListener {
//                val intent = Intent(requireContext(), ReviewPengajuanCutiActivity::class.java)
//                startActivity(intent)
//                Log.d("button", "Review Pengajuan Cuti")
//            }
//
//            btnIzinSakitPegawai.setOnClickListener {
//                val intent = Intent(requireContext(), IzinSakitPegawaiActivity::class.java)
//                startActivity(intent)
//                Log.d("button", "Izin Sakit Pegawai")
//            }
//
//            btnReviewReimbursement.setOnClickListener {
//                val intent = Intent(requireContext(), ReviewReimbursementActivity::class.java)
//                startActivity(intent)
//                Log.d("button", "Review Reimbursement")
//            }
        }
    }

}