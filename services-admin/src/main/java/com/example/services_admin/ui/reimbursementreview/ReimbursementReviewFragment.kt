package com.example.services_admin.ui.reimbursementreview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.services_admin.databinding.FragmentReimbursementReviewBinding

class ReimbursementReviewFragment : Fragment() {

    private var _binding: FragmentReimbursementReviewBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentReimbursementReviewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext() as AppCompatActivity).supportActionBar?.apply {
            title = "Review Reimbursement"
            setDisplayHomeAsUpEnabled(true)
        }

        setReimbursementList()
    }

    private fun setReimbursementList() {
        val rvReimbursementListAdapter = ReimbursementListAdapter()

        binding?.rvReviewReimbursementList?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvReviewReimbursementList?.adapter = rvReimbursementListAdapter

//        rvBookListAdapter.addBookList(BookList.bookList)
    }
}