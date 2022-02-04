package com.puppypedia.common_adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.puppypedia.R
import com.puppypedia.model.GetHealthListModel
import com.puppypedia.ui.main.ui.addhealthproblem.AddHealthDetails
import com.puppypedia.utils.helper.others.Constants
import kotlinx.android.synthetic.main.item_health_details.view.*

class AddHealthListAdapter(var list: ArrayList<GetHealthListModel.Body>) : RecyclerView.Adapter<AddHealthListAdapter.ViewHolder>() {
    lateinit var ctx : Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddHealthListAdapter.ViewHolder {
        ctx = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_health_details, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddHealthListAdapter.ViewHolder, position: Int) {
        holder.itemView.iv_edit.setOnClickListener {
            val intent = Intent(ctx,AddHealthDetails::class.java)
            intent.putExtra("data",list[position])
             ctx.startActivity(intent)
        }
        holder.itemView.tv_record.text = list[position].petName
      //  holder.itemView.tvDescription.text = list[position].description
        if (list[position].image1!="") {
            Glide.with(ctx)
                .load(Constants.PET_IMAGE_URL +list[position].image1)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // It will cache your image after loaded for first time
                .placeholder(R.drawable.place_holder)
                .into(holder.itemView.img)
        } else if (list[position].image2!=""){
            Glide.with(ctx)
                .load(Constants.PET_IMAGE_URL +list[position].image2)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // It will cache your image after loaded for first time
                .placeholder(R.drawable.logo)
                .into(holder.itemView.img)
        }
    }

    override fun getItemCount(): Int {
      return list.size
      }

    inner class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){}
}