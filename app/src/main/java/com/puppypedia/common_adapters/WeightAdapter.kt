package com.puppypedia.common_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.databinding.ItemWeightBinding

class WeightAdapter : RecyclerView.Adapter<WeightAdapter.WeightViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeightViewHolder {
        val bind = ItemWeightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeightViewHolder(bind)
    }

    override fun onBindViewHolder(holder: WeightViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 4
    }

    inner class WeightViewHolder(binding: ItemWeightBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val tvWeight = binding.tvWeight
        val tvAge = binding.tvAge
        val tvDate = binding.tvDate
        val tvTime = binding.tvTime

        fun bind(pos: Int) {

        }
    }

}