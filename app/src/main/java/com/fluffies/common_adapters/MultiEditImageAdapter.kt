package com.fluffies.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fluffies.R
import com.fluffies.ui.main.ui.category_detail.GetPetResponse
import kotlinx.android.synthetic.main.rv_multi_images.view.*

class MultiEditImageAdapter(
    var context: Context,
    var list: GetPetResponse.Body?,
    var type: String
) :
    RecyclerView.Adapter<MultiEditImageAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_multi_images, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Glide.with(context).load(list!!.petImages[position].petImage).placeholder(R.drawable.puppypediamain)
            .into(holder.itemView.ivImage)
    }

    override fun getItemCount(): Int {
        return list!!.petImages.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
    }

}