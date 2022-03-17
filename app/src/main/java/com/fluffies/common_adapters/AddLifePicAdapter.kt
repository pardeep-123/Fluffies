package com.fluffies.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fluffies.R
import kotlinx.android.synthetic.main.item_addlifepic.view.*

class AddLifePicAdapter(var onImageClick: OnImageClick,var list : ArrayList<String>) : RecyclerView.Adapter<AddLifePicAdapter.ViewHolder>() {
    lateinit var ctx : Context

    interface OnImageClick{
        fun onImageClick()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddLifePicAdapter.ViewHolder {
        ctx = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_addlifepic, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddLifePicAdapter.ViewHolder, position: Int) {
//        if (position == list.size) {
//            Glide.with(ctx).load(R.drawable.plusbg)
//                .into(holder.itemView.civPet)
//        } else {
//            Glide.with(ctx).load(Constants.PET_IMAGE_URL + list[position])
//                .placeholder(R.drawable.place_holder).into(holder.itemView.civPet)
//        }
        if (position==0) {
            holder.itemView.ivDogImgPlus.visibility = View.VISIBLE
            holder.itemView.ivDogImg.visibility = View.GONE
            Glide.with(ctx).load(R.drawable.plusbg)
                .into(holder.itemView.ivDogImgPlus)
        }else{
            holder.itemView.ivDogImgPlus.visibility = View.GONE
            holder.itemView.ivDogImg.visibility = View.VISIBLE
            Glide.with(ctx)
                .load(list[position-1])
                .diskCacheStrategy(DiskCacheStrategy.ALL) // It will cache your image after loaded for first time
                .placeholder(R.drawable.place_holder)
                .into(holder.itemView.ivDogImg)
        }
         holder.itemView.setOnClickListener {
             onImageClick.onImageClick()
         }
    }

    override fun getItemCount(): Int {

        return list.size+1
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}