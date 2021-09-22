package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import com.puppypedia.ui.fragments.calender.CalenderReminderResponse


class AppointmentAdapter(
    var context: Context,
    var reminderList: CalenderReminderResponse
) :
    RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_appointment, parent, false)
        return AppointmentViewHolder(v)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        //holder.itemView.tvName?.setText(reminderList.body[position])

    }

    override fun getItemCount(): Int {
        return 3
    }

    inner class AppointmentViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
/*
        val swReminder = swReminder
        val tvName = tvName
        val tvDesc = tvDesc*/
        /* fun bind(pos: Int) {

         }*/
    }

}

