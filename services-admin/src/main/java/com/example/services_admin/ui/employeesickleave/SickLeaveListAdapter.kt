package com.example.services_admin.ui.employeesickleave

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.services_admin.databinding.IzinSakitPegawaiItemLayoutBinding

class SickLeaveListAdapter: RecyclerView.Adapter<SickLeaveListAdapter.ViewHolder>() {

    //    private var listOfTouristAttraction = ArrayList<ListTouristAttractionItem>()
//
//    fun addTouristAttraction(list: List<ListTouristAttractionItem>) {
//        this.listOfTouristAttraction.clear()
//        this.listOfTouristAttraction.addAll(list)
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(private val binding: IzinSakitPegawaiItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            IzinSakitPegawaiItemLayoutBinding.inflate(
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