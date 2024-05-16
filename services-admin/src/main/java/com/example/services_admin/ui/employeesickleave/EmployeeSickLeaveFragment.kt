package com.example.services_admin.ui.employeesickleave

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.services_admin.databinding.FragmentEmployeeSickLeaveBinding

class EmployeeSickLeaveFragment : Fragment() {
    private var _binding: FragmentEmployeeSickLeaveBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentEmployeeSickLeaveBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext() as AppCompatActivity).supportActionBar?.apply {
            title = "Izin Sakit Pegawai"
            setDisplayHomeAsUpEnabled(true)
        }

        setSickLeaveList()
    }

    private fun setSickLeaveList() {
        val rvSickLeaveListAdapter = SickLeaveListAdapter()

        binding?.rvIzinSakitPegawaiList?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvIzinSakitPegawaiList?.adapter = rvSickLeaveListAdapter

//        rvBookListAdapter.addBookList(BookList.bookList)
    }

}