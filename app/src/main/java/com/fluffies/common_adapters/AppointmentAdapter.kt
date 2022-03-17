package com.fluffies.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.fluffies.R
import com.fluffies.ui.fragments.calender.CalendarDataModel
import kotlinx.android.synthetic.main.item_appointment.view.*


class AppointmentAdapter(
    var context: Context,
    var reminderList: ArrayList<CalendarDataModel>,
    var checkChangeClickCallBack: CheckChangeClickCallBack
) :
    RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_appointment, parent, false)
        return AppointmentViewHolder(v)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.itemView.tvName.text = reminderList[position].name
        holder.itemView.tvDesc.text = "Dog - " + reminderList[position].petName + " At " + reminderList[position].date + " "+ reminderList[position].time
        holder.itemView.swReminder.isChecked = reminderList[position].isRemender == 1

        holder.itemView.swReminder.setOnCheckedChangeListener { compoundButton, b ->
            checkChangeClickCallBack.onItemClick(position, b)

        }
    }

    override fun getItemCount(): Int {
        return reminderList.size
    }

    inner class AppointmentViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val viewForeground : RelativeLayout = itemView.findViewById(R.id.view_foreground)
        val viewBackground : RelativeLayout = itemView.findViewById(R.id.view_background)
/*
        val swReminder = swReminder
        val tvName = tvName
        val tvDesc = tvDesc*/
        /* fun bind(pos: Int) {

         }*/
    }

}

