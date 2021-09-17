package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.puppypedia.R
import com.puppypedia.model.DogsModel
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import com.puppypedia.utils.helper.others.SharedPrefUtil
import kotlinx.android.synthetic.main.item_your_dogs.view.*

class DogsAdapter(
    var context: Context,
    var arrayList: ArrayList<HomeFragmentResponse.Body.Pet>


) :
    RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    var onItemSelected: ((dogModel: DogsModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsAdapter.DogsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_your_dogs, parent, false)
        return DogsViewHolder(view)
    }
    var selectedpoz = 0
    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        /*   Glide.with(context).load("http://202.164.42.227:7700" +datalist.body.category[position].image).placeholder(R.drawable.icon3).into(holder.itemView.ivService)
           holder.itemView.tv_service.setText(datalist.body.category.get(position).name)
           */


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



            tvDogName.text = arrayList[pos].name
            Glide.with(context)
                .load(arrayList[pos].image)
                .error(R.drawable.place_holder)
                .into(ivDog)

            itemView.setOnClickListener {

                arrayList.forEachIndexed { index, dogsModel ->
                    selectedpoz = pos
                    arrayList[pos].selected = true
                    arrayList[selectedpoz].selected = false
                    SharedPrefUtil.getInstance().savePetId(arrayList[pos].id.toString())
                    notifyDataSetChanged()
                }
            }
        }
    }
}