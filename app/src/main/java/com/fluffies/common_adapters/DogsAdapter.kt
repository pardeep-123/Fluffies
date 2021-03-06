package com.fluffies.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fluffies.R
import com.fluffies.ui.fragments.home.HomeFragmentResponse
import com.fluffies.utils.helper.others.Constants
import com.fluffies.utils.helper.others.SharedPrefUtil
import kotlinx.android.synthetic.main.item_your_dogs.view.*

class DogsAdapter(
    var context: Context,
    var arrayList: ArrayList<HomeFragmentResponse.Body.Pet>,
    var selectedPos :Int,
    var clickCallBack: ClickCallBackNew) :

    RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsAdapter.DogsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.items_dog_list, parent, false)
        return DogsViewHolder(view)
    }

    var selectedpoz = 0
    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {

        holder.bind(position)
    }

    override fun getItemCount(): Int {
        //  return datalist.body.pets.size
        return arrayList.size
    }

    inner class DogsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val ivDog = itemView.ivDog
        val tvDogName = itemView.tvDogName
        val ivCheck = itemView.ivCheck

        fun bind(pos: Int) {
            if (selectedpoz == pos) {
                SharedPrefUtil.getInstance().savePetId(arrayList[pos].id.toString())
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

            tvDogName.text = arrayList[pos].name
            Glide.with(context)
                .load(Constants.IMAGE_URL + arrayList[pos].image)
                .error(R.drawable.place_holder)
                .into(ivDog)

            itemView.setOnClickListener {
                arrayList.forEachIndexed { index, dogsModel ->
                    selectedpoz = pos
                    arrayList[pos].selected = true
                    arrayList[selectedpoz].selected = false
                    notifyDataSetChanged()
                    clickCallBack.onItemClick(selectedPos,pos, "pet")
                }
            }
        }
    }
}
