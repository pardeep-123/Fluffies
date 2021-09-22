package com.puppypedia.common_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.puppypedia.R
import com.puppypedia.ui.fragments.home.HomeFragment
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import kotlinx.android.synthetic.main.item_home.view.*


class HomeAdapter(
    var context: HomeFragment,
    var datalist: HomeFragmentResponse
) : RecyclerView.Adapter<HomeAdapter.Vh?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return Vh(v)
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.itemView.tvDescription.setText(datalist.body.banners[position].description)
        Glide.with(context)
            .load("http://202.164.42.227:7700" + datalist.body.banners[position].image)
            .placeholder(R.drawable.dogsimg).into(holder.itemView.details_img)
    }
    override fun getItemCount(): Int {
        return datalist.body.banners.size
    }

    inner class Vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var img: ImageView

    }
}
