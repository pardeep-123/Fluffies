package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import com.puppypedia.ui.fragments.weight.GetWeightResponse
import kotlinx.android.synthetic.main.item_weight.view.*

class WeightAdapter(var context: Context, var weightlist: GetWeightResponse) :

    RecyclerView.Adapter<WeightAdapter.WeightViewHolder>() {
    var onItemClick: ((pos: Int) -> Unit)? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeightViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_weight, parent, false)
        return WeightViewHolder(v)
    }

    override fun onBindViewHolder(holder: WeightAdapter.WeightViewHolder, position: Int) {
        holder.itemView.tvWeight.setText(weightlist.body.weightCharts[position].weight + " lbs")
        holder.itemView.tvAge.setText(weightlist.body.weightCharts[position].age)
        holder.itemView.tvDate.setText(weightlist.body.weightCharts[position].date)
        holder.itemView.tvTime.setText(weightlist.body.weightCharts[position].time)

    }

    override fun getItemCount(): Int {
        return weightlist.body.weightCharts.size
    }

    inner class WeightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*    RecyclerView.ViewHolder(binding.root) {
            val tvWeight = binding.tvWeight
            val tvAge = binding.tvAge
            val tvDate = binding.tvDate
            val tvTime = binding.tvTime
            fun bind(pos: Int) {}*/
    }
}