package com.puppypedia.common_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import com.puppypedia.databinding.ItemYourDogsBinding
import com.puppypedia.model.DogsModel

class DogsAdapter(var arrayList: ArrayList<DogsModel>) :
    RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    var onItemSelected: ((dogModel: DogsModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val bind = ItemYourDogsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogsViewHolder(bind)
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 2
    }

    inner class DogsViewHolder(binding: ItemYourDogsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val ivDog = binding.ivDog
        val tvDogName = binding.tvDogName
        val ivCheck = binding.ivCheck

        fun bind(pos: Int) {
            val dogModel = arrayList[pos]

            if (dogModel.isSelected) {
                ivCheck.setImageDrawable(ContextCompat.getDrawable(ivCheck.context,R.drawable.ic_dot_unselect))
            } else {
                ivCheck.setImageDrawable(ContextCompat.getDrawable(ivCheck.context,R.drawable.ic_dot_select))
            }


            ivDog.setImageResource(dogModel.dogImage)
            tvDogName.text = dogModel.dogName

            ivCheck.setOnClickListener {
                arrayList.forEachIndexed { index, dogsModel ->
                    dogsModel.isSelected = index == pos

                    notifyDataSetChanged()
                }
            }
        }
    }
}