package com.example.services_admin.ui.employeesalary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.services_admin.databinding.FragmentEmployeeSalaryDetailBinding

class EmployeeSalaryDetailFragment : Fragment() {
    private var _binding: FragmentEmployeeSalaryDetailBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentEmployeeSalaryDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext() as AppCompatActivity).supportActionBar?.apply {
            title = "Detail Gaji Pegawai"
            setDisplayHomeAsUpEnabled(true)
        }
    }
}