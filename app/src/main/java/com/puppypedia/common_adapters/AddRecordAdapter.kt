package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.puppypedia.R
import com.puppypedia.ui.main.ui.category_detail.GetPetResponse
import com.puppypedia.utils.helper.others.Constants
import kotlinx.android.synthetic.main.add_record_layout.view.*

class AddRecordAdapter(
    var context: Context,
    var dataList: GetPetResponse,
    var clickCallBack: ClickCallBack
) :
    RecyclerView.Adapter<AddRecordAdapter.ViewHolderGrid>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGrid {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.add_record_layout, parent, false)
        return ViewHolderGrid(view)
    }

    override fun onBindViewHolder(holder: ViewHolderGrid, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Constants.PET_IMAGE_URL + dataList.body[position].petImages[0].petImage)
            .placeholder(R.drawable.place_holder).into(holder.itemView.iv_record)

        holder.itemView.tv_record.setText(dataList.body[position].description)
        holder.iv_delete.setOnClickListener {
            clickCallBack.onItemClick(position, "3")
        }
        holder.iv_edit.setOnClickListener {
            clickCallBack.onItemClick(position, "2")
        }
    }
    override fun getItemCount(): Int {
        return dataList.body.size
    }
    class ViewHolderGrid(item1: View) : RecyclerView.ViewHolder(item1) {
        val iv_record: ImageView = item1.findViewById(R.id.iv_record)
        val tv_record: TextView = item1.findViewById(R.id.tv_record)
        val iv_delete: ImageView = item1.findViewById(R.id.iv_delete)
        val iv_edit: ImageView = item1.findViewById(R.id.iv_edit)


    }

}
