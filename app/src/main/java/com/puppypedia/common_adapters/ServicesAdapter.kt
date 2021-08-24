package com.puppypedia.common_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import com.puppypedia.model.ServicesModel
import java.util.ArrayList

class ServicesAdapter(datalist: ArrayList<ServicesModel>): RecyclerView.Adapter<ServicesAdapter.Vh?>()  {
    lateinit var datalist: ArrayList<ServicesModel>

    inner class Vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView
        var tv: TextView
        init {
            img = itemView.findViewById(R.id.image_service)
            tv = itemView.findViewById(R.id.tv_service)
            // videoPic= itemView.findViewById(R.id.iv_videoicon);
        }

        fun onBind(pos: Int){
            val serviceModel = datalist[pos]
            img.setImageResource(datalist[position].img)
            tv.text = serviceModel.service
        }

    }
    init {
        this.datalist = datalist
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesAdapter.Vh {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_services, parent, false)
        return Vh(v)
    }

    override fun onBindViewHolder(holder: ServicesAdapter.Vh, position: Int) {
        holder.onBind(position)


    }

    override fun getItemCount(): Int {
       return datalist.size
    }
}