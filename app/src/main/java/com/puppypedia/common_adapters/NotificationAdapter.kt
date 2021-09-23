package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import com.puppypedia.ui.main.ui.notification.NotificationResponse
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationAdapter(
    var context: Context,
    var notiList: NotificationResponse
) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NotificationAdapter.NotificationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.itemView.tvDescription.setText(notiList.body[position].message)
        holder.itemView.tvDate.setText(notiList.body[position].createdAt)
        holder.onbind(position)
    }
    override fun getItemCount(): Int {
        return notiList.body.size
    }
    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.tvName
        val tvDescription: TextView = itemView.tvDescription
        val tvDate: TextView = itemView.tvDate

        fun onbind(position: Int) {
            if (position == 0) {
                itemView.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_layout_radius_colored)
                tvName.setTextColor(ContextCompat.getColor(context, R.color.white))
                tvDate.setTextColor(ContextCompat.getColor(context, R.color.white))
                tvDescription.setTextColor(ContextCompat.getColor(context, R.color.white))
            }else {
                itemView.background = ContextCompat.getDrawable(context,R.drawable.bg_layout_radius)
              tvName.setTextColor(ContextCompat.getColor(context,R.color.black))
                tvDate.setTextColor(ContextCompat.getColor(context, R.color.black))
                tvDescription.setTextColor(ContextCompat.getColor(context, R.color.black))
            }

        }
    }
}