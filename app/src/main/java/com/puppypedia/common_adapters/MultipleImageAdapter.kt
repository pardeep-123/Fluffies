package com.puppypedia.common_adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.puppypedia.R
import com.yanzhenjie.album.AlbumFile
import kotlinx.android.synthetic.main.rv_multi_images.view.*
import java.util.*

class MultipleImageAdapter(var context: Context, var list: ArrayList<AlbumFile>, var type: String) :
    RecyclerView.Adapter<MultipleImageAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_multi_images, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.iv_delete.visibility = View.VISIBLE
        Log.e("checkimagelink", "--" + list.get(position).path)
        Glide.with(context).load(list.get(position).path)
            .dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.puppypediamain)
            .into(holder.itemView.ivImage)

        holder.itemView.iv_delete.setOnClickListener {
            list.remove(list.get(position))
            notifyDataSetChanged()
        }
    }

    interface updatedSkilllist {
        fun updatedSkilllist(list: ArrayList<AlbumFile>)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
    }

}