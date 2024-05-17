package com.example.services_employee.ui.leaveapplicationhistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.services_employee.databinding.RiwayatPengajuanCutiItemLayoutBinding

class LeaveApplicationHistoryAdapter :
    RecyclerView.Adapter<LeaveApplicationHistoryAdapter.ViewHolder>() {

//    private var listOfTouristAttraction = ArrayList<ListTouristAttractionItem>()
//
//    fun addTouristAttraction(list: List<ListTouristAttractionItem>) {
//        this.listOfTouristAttraction.clear()
//        this.listOfTouristAttraction.addAll(list)
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(private val binding: RiwayatPengajuanCutiItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RiwayatPengajuanCutiItemLayoutBinding.inflate(
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