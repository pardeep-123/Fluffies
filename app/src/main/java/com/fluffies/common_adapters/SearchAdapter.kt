package com.fluffies.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fluffies.R
import com.fluffies.ui.fragments.home.HomeFragmentResponse
import kotlinx.android.synthetic.main.item_services.view.*


class SearchAdapter(
    var context: Context,
    var datalist: ArrayList<HomeFragmentResponse.Body.Category>,
    var clickCallBack: ClickCallBack
) :
    RecyclerView.Adapter<SearchAdapter.ServicesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.ServicesViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_services, parent, false)
        return ServicesViewHolder(v)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ServicesViewHolder, position: Int) {

        Glide.with(context)
            .load("http://202.164.42.227:7700" + datalist[position].image)
            .placeholder(R.drawable.icon3).into(holder.itemView.ivService)
        holder.itemView.tv_service.setText(datalist.get(position).name)

        holder.itemView.setOnClickListener {
            clickCallBack.onItemClick(position, "cat")
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    inner class ServicesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.ivService)
        var tv: TextView = itemView.findViewById(R.id.tv_service)

    }
}