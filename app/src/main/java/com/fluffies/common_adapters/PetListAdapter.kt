package com.fluffies.common_adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fluffies.R
import com.fluffies.ui.main.ui.mypetprofile.PetProfileResponse
import com.fluffies.utils.helper.others.Constants.Companion.IMAGE_URL
import kotlinx.android.synthetic.main.item_your_dogs.view.*

class PetListAdapter(
    var context: Context,
    var arrayList: PetProfileResponse,
    var clickCallBack: ClickCallBack
) :
    RecyclerView.Adapter<PetListAdapter.StatusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_your_dogs, parent, false)
        return StatusViewHolder(view)
    }
    var selectedpoz = 0
    var pos = 0

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return arrayList.body.size
    }

    inner class StatusViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val ivCheck = itemView.ivCheck

        @SuppressLint("NotifyDataSetChanged")
        fun bind(pos: Int) {
            Glide.with(context).load(IMAGE_URL + arrayList.body[position].image)
                .placeholder(R.drawable.place_holder).into(itemView.ivDog)
            itemView.tvDogName.setText(arrayList.body[position].name)

            if (selectedpoz == pos) {
                //   SharedPrefUtil.getInstance().savePetId(arrayList.body[pos].id.toString()
                ivCheck.setImageDrawable(
                    ContextCompat.getDrawable(
                        ivCheck.context,
                        R.drawable.ic_dot_select
                    )
                )
            } else {
                ivCheck.setImageDrawable(
                    ContextCompat.getDrawable(
                        ivCheck.context,
                        R.drawable.ic_dot_unselect
                    )
                )
            }
            itemView.setOnClickListener {
                arrayList.body.forEachIndexed { index, dogsModel ->
                    selectedpoz = pos
                    arrayList.body[pos].selected = true
                    arrayList.body[selectedpoz].selected = false

                    notifyDataSetChanged()
                    //  clickCallBack.onItemClick(pos, "pet")
                }
                clickCallBack.onItemClick(pos, "pet")

            }
        }
    }
}