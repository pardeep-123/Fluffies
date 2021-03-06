package com.fluffies.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fluffies.R
import com.fluffies.ui.main.ui.mypetprofile.PetProfileResponse
import com.fluffies.utils.helper.others.Constants.Companion.PET_IMAGE_URL
import de.hdodenhof.circleimageview.CircleImageView

import kotlinx.android.synthetic.main.item_status.view.*

class StatusAdapter(
    var context: Context,
    var arrayList: PetProfileResponse, var onProfileClick: OnProfileClick) :
    RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {

    interface OnProfileClick{
        fun onimageClick(myPetId:String,position: Int)
        fun onitemClick(name:String,value: String)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_status, parent, false)
        return StatusViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        if (position == arrayList.body.size) {
            Glide.with(context).load(R.drawable.plusbg)
                .into(holder.itemView.civPet)
        } else {
            Glide.with(context).load(PET_IMAGE_URL + arrayList.body[position].image)
                .placeholder(R.drawable.place_holder).into(holder.itemView.civPet)
        }
        holder.itemView.setOnClickListener {
            if (position == arrayList.body.size) {

                onProfileClick.onitemClick("add","add")

            } else {
                  onProfileClick.onimageClick(arrayList.body[position].id.toString(),position)

            }
        }
    }
    override fun getItemCount(): Int {
        return arrayList.body.size + 1
    }
    inner class StatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val civPet: CircleImageView = itemView.civPet
    }
}