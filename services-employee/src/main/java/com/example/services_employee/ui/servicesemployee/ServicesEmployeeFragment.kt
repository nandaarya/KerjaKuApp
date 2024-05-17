package com.example.services_employee.ui.servicesemployee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.services_employee.databinding.FragmentServicesEmployeeBinding

class ServicesEmployeeFragment : Fragment() {

    private var _binding: FragmentServicesEmployeeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentServicesEmployeeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButton()
    }

    private fun setupButton() {
        binding?.apply {

            btnPengajuanCuti.setOnClickListener {
                val action =
                    ServicesEmployeeFragmentDirections.actionServicesEmployeeFragmentToLeaveApplicationFragment()
                findNavController().navigate(action)
            }

//            btnIzinSakit.setOnClickListener {
//                val intent = Intent(requireContext(), IzinSakitActivity::class.java)
//                startActivity(intent)
//                Log.d("button", "Izin Sakit")
//            }
//
//            btnReimbursement.setOnClickListener {
//                val intent = Intent(requireContext(), ReimbursementActivity::class.java)
//                startActivity(intent)
//                Log.d("button", "Reimbursement")
//            }
//
//            btnRiwayatPengajuanCuti.setOnClickListener {
//                val intent = Intent(requireContext(), RiwayatPengajuanCutiActivity::class.java)
//                startActivity(intent)
//                Log.d("button", "Riwayat Pengajuan Cuti")
//            }
        }
    }

}