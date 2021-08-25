package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import com.puppypedia.model.ServicesModel
import java.util.ArrayList

class ServicesAdapter(var datalist: ArrayList<ServicesModel>) :
    RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>() {

    var onItemClick:((serviceModel:ServicesModel)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesAdapter.ServicesViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_services, parent, false)
        return ServicesViewHolder(v)
    }

    override fun onBindViewHolder(holder: ServicesAdapter.ServicesViewHolder, position: Int) {
        holder.onBind(position)


    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    inner class ServicesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.ivService)
        var tv: TextView = itemView.findViewById(R.id.tv_service)

        fun onBind(pos: Int) {
            val serviceModel = datalist[pos]
            img.setImageResource(datalist[pos].img)
            tv.text = serviceModel.service

            if (serviceModel.isSelected) {
                tv.setTextColor(tv.context.getColor(R.color.theme_Color))
                itemView.background = ContextCompat.getDrawable(itemView.context,R.drawable.bg_white_corners_10dp)
            } else {
                tv.setTextColor(tv.context.getColor(R.color.white))
                itemView.background = ContextCompat.getDrawable(itemView.context,R.drawable.bg_sky_blue_10dp)
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(serviceModel)
            }
        }

    }
}