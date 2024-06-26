package com.example.services_admin.ui.employeesalary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.services_admin.databinding.SearchResultItemLayoutBinding

class EmployeeSalaryListAdapter(private val navController: NavController) : RecyclerView.Adapter<EmployeeSalaryListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: SearchResultItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.cvSearchResultItemLayout.setOnClickListener {
                val action = EmployeeSalaryFragmentDirections.actionEmployeeSalaryFragmentToEmployeeSalaryDetailFragment()
                navController.navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchResultItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}