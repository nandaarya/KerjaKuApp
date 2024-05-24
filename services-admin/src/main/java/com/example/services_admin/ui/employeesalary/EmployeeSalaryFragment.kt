package com.example.services_admin.ui.employeesalary

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.services_admin.databinding.FragmentEmployeeSalaryBinding

class EmployeeSalaryFragment : Fragment() {

    private var _binding: FragmentEmployeeSalaryBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): CoordinatorLayout? {
        _binding = FragmentEmployeeSalaryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext() as AppCompatActivity).supportActionBar?.apply {
            title = "Gaji Pegawai"
            setDisplayHomeAsUpEnabled(true)
        }

        setSearchResultData()
    }

    private fun setSearchResultData() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding?.rvSearchResult?.layoutManager = layoutManager
        val adapter = EmployeeSalaryListAdapter()
        binding?.rvSearchResult?.adapter = adapter

        binding?.apply {
            searchViewResult.setupWithSearchBar(searchBar)
            searchViewResult.editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // Do something before text changes
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Do something while text is changing
                    Log.d("search view", s?.toString() ?: "Text Kosong")
                }

                override fun afterTextChanged(s: Editable?) {
                    // Do something after text changes
                }
            })
        }
    }

}