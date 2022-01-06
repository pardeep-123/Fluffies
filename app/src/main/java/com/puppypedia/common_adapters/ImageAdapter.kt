package com.puppypedia.common_adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.puppypedia.R
import com.puppypedia.ui.main.ui.add_record.AddRecordActivity
import com.puppypedia.ui.main.ui.category_detail.GetPetResponse
import com.puppypedia.utils.helper.others.Constants
import kotlinx.android.synthetic.main.res_image.view.iv_delete
import kotlinx.android.synthetic.main.rv_multi_images.view.*

class ImageAdapter(
    var context: Context,
    var list: ArrayList<GetPetResponse.Body.PetImage>,
    var addRecordActivity: AddRecordActivity
) :
    RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_multi_images, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.e("chcekurll", "++++" + Constants.PET_IMAGE_URL + list.get(position).petImage)
        Glide.with(context)
            .load(Constants.PET_IMAGE_URL + list.get(position).petImage)
            .placeholder(R.drawable.logo)
            .into(holder.itemView.ivImage)

        holder.itemView.iv_delete.setOnClickListener {
            addRecordActivity.deleteimageapi(
                list[position].postId.toString(),
                list.get(position).id.toString()
            )
            list.remove(list.get(position))

            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface deleteimage {
        fun deleteimageapi(postid: String, imageid: String)
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
    }
}