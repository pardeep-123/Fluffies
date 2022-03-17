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
import com.fluffies.utils.helper.others.Constants.Companion.IMAGE_URL
import kotlinx.android.synthetic.main.item_services.view.*


class ServicesAdapter(
    var context: Context,
    var datalist: HomeFragmentResponse,
    var clickCallBack: ClickCallBack,
    var idata: Int
) :
    RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServicesAdapter.ServicesViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_services, parent, false)
        return ServicesViewHolder(v)
    }

    override fun onBindViewHolder(holder: ServicesAdapter.ServicesViewHolder, position: Int) {
        // holder.onBind(position)
        Glide.with(context)
            .load("$IMAGE_URL${datalist.body.category[position].image}")
            .placeholder(R.drawable.icon3).into(holder.itemView.ivService)
        holder.itemView.tv_service.text = datalist.body.category[position].name

        holder.itemView.setOnClickListener {
            clickCallBack.onItemClick(position, "cat")
        }
    }
    override fun getItemCount(): Int {
        return if (idata == 1) {
            6
        } else {
            datalist.body.category.size
        }
    }
    inner class ServicesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.ivService)
        var tv: TextView = itemView.findViewById(R.id.tv_service)
//        fun onBind(pos: Int) {
//           val serviceModel = datalist[pos]
//            img.setImageResource(datalist[pos].img)
//            tv.text = serviceModel.service
//
//            if (serviceModel.isSelected) {
//                tv.setTextColor(tv.context.getColor(R.color.theme_Color))
//                itemView.background = ContextCompat.getDrawable(itemView.context,R.drawable.bg_white_corners_10dp)
//            } else {
//                tv.setTextColor(tv.context.getColor(R.color.white))
//                itemView.background = ContextCompat.getDrawable(itemView.context,R.drawable.bg_sky_blue_10dp)
//            }
//
//            itemView.setOnClickListener {
//                onItemClick?.invoke(serviceModel)
//            }
//        }

    }


}