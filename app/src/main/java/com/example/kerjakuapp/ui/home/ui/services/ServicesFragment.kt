package com.example.kerjakuapp.ui.home.ui.services

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kerjakuapp.databinding.FragmentServicesBinding
import com.example.kerjakuapp.ui.izinsakit.IzinSakitActivity
import com.example.kerjakuapp.ui.pengajuancuti.PengajuanCutiActivity
import com.example.kerjakuapp.ui.reimbursement.ReimbursementActivity
import com.example.kerjakuapp.ui.riwayatpengajuancuti.RiwayatPengajuanCutiActivity
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

        val servicesPegawai = binding.servicesPegawai
        val servicesAdmin = binding.servicesAdmin

        // Atur visibilitas berdasarkan kondisi
        if ("admin" == "admin") {
            servicesAdmin.root.visibility = View.VISIBLE
            servicesPegawai.root.visibility = View.GONE
        } else {
            servicesAdmin.root.visibility = View.GONE
            servicesPegawai.root.visibility = View.VISIBLE
        }

        servicesPegawai.btnIzinSakit.setOnClickListener{
            val intent = Intent(requireContext(), IzinSakitActivity::class.java)
            startActivity(intent)
            Log.d("button", "Izin Sakit")
        }

        servicesPegawai.btnPengajuanCuti.setOnClickListener{
            val intent = Intent(requireContext(), PengajuanCutiActivity::class.java)
            startActivity(intent)
            Log.d("button", "Pengajuan Cuti")
        }

        servicesPegawai.btnReimbursement.setOnClickListener{
            val intent = Intent(requireContext(), ReimbursementActivity::class.java)
            startActivity(intent)
            Log.d("button", "Reimbursement")
        }

        servicesPegawai.btnRiwayatPengajuanCuti.setOnClickListener{
            val intent = Intent(requireContext(), RiwayatPengajuanCutiActivity::class.java)
            startActivity(intent)
            Log.d("button", "Riwayat Pengajuan Cuti")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}