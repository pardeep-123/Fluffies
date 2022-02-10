package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.puppypedia.R
import com.puppypedia.click
import com.puppypedia.model.GetImageModel
import com.puppypedia.utils.helper.others.Constants
import kotlinx.android.synthetic.main.items_pictures.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class PictureAdapter(var onDeletePic: OnDeletePic,var list: ArrayList<GetImageModel.Body>) : RecyclerView.Adapter<PictureAdapter.ViewHolder>() {
     lateinit var ctx : Context
    interface OnDeletePic{
        fun onDeletePic(id:String,position: Int)
        fun onOpenPic(path:String)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.items_pictures, parent, false)
         ctx = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: PictureAdapter.ViewHolder, position: Int) {
        Glide.with(ctx).load(Constants.PET_IMAGE_URL + list[position].petImage).placeholder(R.drawable.puppypediamain)
            .into(holder.itemView.ivImage)

        holder.itemView.iv_delete.setOnClickListener {
            onDeletePic.onDeletePic(list[position].id.toString(),position)
        }
        holder.itemView.click {
            onDeletePic.onOpenPic(list[position].petImage)
        }
    }

    override fun getItemCount(): Int {
     return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}