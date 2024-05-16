package com.example.services_admin.ui.employeeleavereview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.services_admin.databinding.FragmentEmployeeLeaveReviewBinding

class EmployeeLeaveReviewFragment : Fragment() {

    private var _binding: FragmentEmployeeLeaveReviewBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentEmployeeLeaveReviewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext() as AppCompatActivity).supportActionBar?.apply {
            title = "Review Pengajuan Cuti"
            setDisplayHomeAsUpEnabled(true)
        }

        setPengajuanCutiList()
    }

    private fun setPengajuanCutiList() {
        val rvPengajuanCutiListAdapter = PengajuanCutiListAdapter()

        binding?.rvReviewPengajuanCutiList?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvReviewPengajuanCutiList?.adapter = rvPengajuanCutiListAdapter

//        rvBookListAdapter.addBookList(BookList.bookList)
    }
}