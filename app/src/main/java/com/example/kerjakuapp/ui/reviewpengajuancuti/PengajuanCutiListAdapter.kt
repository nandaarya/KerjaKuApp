package com.example.kerjakuapp.ui.reviewpengajuancuti

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kerjakuapp.databinding.RiwayatPengajuanCutiItemLayoutBinding

class PengajuanCutiListAdapter :
    RecyclerView.Adapter<PengajuanCutiListAdapter.ViewHolder>() {

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