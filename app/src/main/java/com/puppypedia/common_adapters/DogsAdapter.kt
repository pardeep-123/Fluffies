package com.puppypedia.common_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import com.puppypedia.model.DogsModel
import kotlinx.android.synthetic.main.item_your_dogs.view.*

class DogsAdapter(
    var arrayList: ArrayList<DogsModel>


) :
    RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    var onItemSelected: ((dogModel: DogsModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsAdapter.DogsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_your_dogs, parent, false)
        return DogsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        /*   Glide.with(context).load("http://202.164.42.227:7700" +datalist.body.category[position].image).placeholder(R.drawable.icon3).into(holder.itemView.ivService)
           holder.itemView.tv_service.setText(datalist.body.category.get(position).name)
           */


        holder.bind(position)
    }

    override fun getItemCount(): Int {
        //  return datalist.body.pets.size
        return 2
    }

    inner class DogsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val ivDog = itemView.ivDog
        val tvDogName = itemView.tvDogName
        val ivCheck = itemView.ivCheck

        fun bind(pos: Int) {
            val dogModel = arrayList[pos]

            if (dogModel.isSelected) {
                ivCheck.setImageDrawable(
                    ContextCompat.getDrawable(
                        ivCheck.context,
                        R.drawable.ic_dot_unselect
                    )
                )
            } else {
                ivCheck.setImageDrawable(
                    ContextCompat.getDrawable(
                        ivCheck.context,
                        R.drawable.ic_dot_select
                    )
                )
            }


            ivDog.setImageResource(dogModel.dogImage)
            tvDogName.text = dogModel.dogName

            itemView.setOnClickListener {
                arrayList.forEachIndexed { index, dogsModel ->
                    dogsModel.isSelected = index == pos

                    notifyDataSetChanged()
                }
            }
        }
    }
}