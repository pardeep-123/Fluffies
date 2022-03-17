package com.fluffies.common_adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fluffies.R
import com.fluffies.model.GetLifeEventModel
import com.fluffies.ui.fragments.profile.EditLifeEvent
import com.fluffies.utils.helper.others.Constants
import kotlinx.android.synthetic.main.items_life_event.view.*
import kotlinx.android.synthetic.main.items_pictures.view.ivImage
import kotlinx.android.synthetic.main.items_pictures.view.iv_delete


class GetLifeAdapter(var onDeletePic: OnDeletePic,var list: ArrayList<GetLifeEventModel.Body>) : RecyclerView.Adapter<GetLifeAdapter.ViewHolder>() {
    lateinit var ctx : Context
    interface OnDeletePic{
        fun onDeletePic(id:String,position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.items_life_event, parent, false)
        ctx = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(ctx).load(Constants.PET_IMAGE_URL + list[position].petImages[0].petImage).placeholder(R.drawable.puppypediamain)
            .into(holder.itemView.ivImage)

        holder.itemView.iv_delete.setOnClickListener {
            onDeletePic.onDeletePic(list[position].id.toString(),position)
        }
     // set name and date
        holder.itemView.dateText.text = list[position].date
        holder.itemView.titleText.text = list[position].title
        holder.itemView.setOnClickListener {

            val intent = Intent(ctx, EditLifeEvent::class.java)
            intent.putExtra("lifeData",list[position])
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}