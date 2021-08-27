package com.puppypedia.common_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.databinding.ItemAppointmentBinding
import com.puppypedia.databinding.ItemWeightBinding

class AppointmentAdapter : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val bind = ItemAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppointmentViewHolder(bind)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 3
    }

    inner class AppointmentViewHolder(binding: ItemAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val swReminder = binding.swReminder
        val tvName = binding.tvName
        val tvDesc = binding.tvDesc

        fun bind(pos: Int) {

        }
    }

}