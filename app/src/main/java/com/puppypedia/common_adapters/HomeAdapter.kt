package com.puppypedia.common_adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import com.puppypedia.model.HomeImageModel
import java.util.ArrayList

class HomeAdapter(datalist: ArrayList<HomeImageModel>) : RecyclerView.Adapter<HomeAdapter.Vh?>() {
    var datalist: ArrayList<HomeImageModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return Vh(v)
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.img.setImageResource(datalist[position].img)
    }

    private val itemCount: Int = datalist.size

    override fun getItemCount(): Int {
        return datalist.size
    }

    inner class Vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView
        var videoPic: ImageView? = null

        init {
            img = itemView.findViewById(R.id.details_img)
            // videoPic= itemView.findViewById(R.id.iv_videoicon);
        }
    }

    init {
        this.datalist = datalist
    }
}