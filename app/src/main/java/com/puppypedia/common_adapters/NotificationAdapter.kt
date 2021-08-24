package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationAdapter (var context: Context) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.onbind(position)
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onbind(position: Int){
            if(position == 0){
                itemView.background = ContextCompat.getDrawable(context,R.drawable.bg_layout_radius_colored)
                itemView.tvName.setTextColor(ContextCompat.getColor(context,R.color.white))
                itemView.tvDate.setTextColor(ContextCompat.getColor(context,R.color.white))
                itemView.tvMsg.setTextColor(ContextCompat.getColor(context,R.color.white))
            }else {
                itemView.background = ContextCompat.getDrawable(context,R.drawable.bg_layout_radius)
                itemView.tvName.setTextColor(ContextCompat.getColor(context,R.color.black))
                itemView.tvDate.setTextColor(ContextCompat.getColor(context,R.color.black))
                itemView.tvMsg.setTextColor(ContextCompat.getColor(context,R.color.black))
            }

        }
    }
}