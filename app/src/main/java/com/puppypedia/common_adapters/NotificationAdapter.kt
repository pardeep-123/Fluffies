package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import com.puppypedia.databinding.ItemNotificationBinding

class NotificationAdapter (var context: Context) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = ItemNotificationBinding.inflate( LayoutInflater.from(context),parent,false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.onbind(position)
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class NotificationViewHolder(itemView: ItemNotificationBinding) : RecyclerView.ViewHolder(itemView.root) {

        val tvName : TextView = itemView.tvName
        val tvDate : TextView = itemView.tvDate
        val tvMsg : TextView = itemView.tvMsg

        fun onbind(position: Int){
            if(position == 0){
                itemView.background = ContextCompat.getDrawable(context,R.drawable.bg_layout_radius_colored)
                tvName.setTextColor(ContextCompat.getColor(context,R.color.white))
               tvDate.setTextColor(ContextCompat.getColor(context,R.color.white))
               tvMsg.setTextColor(ContextCompat.getColor(context,R.color.white))
            }else {
                itemView.background = ContextCompat.getDrawable(context,R.drawable.bg_layout_radius)
              tvName.setTextColor(ContextCompat.getColor(context,R.color.black))
              tvDate.setTextColor(ContextCompat.getColor(context,R.color.black))
              tvMsg.setTextColor(ContextCompat.getColor(context,R.color.black))
            }

        }
    }
}