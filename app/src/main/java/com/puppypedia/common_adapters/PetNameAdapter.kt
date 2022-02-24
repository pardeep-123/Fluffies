package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.puppypedia.R
import com.puppypedia.model.PetNameModel
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import kotlinx.android.synthetic.main.item_your_dogs.view.*

class PetNameAdapter(
    private var list: ArrayList<PetNameModel>,
    var dogArrayList: ArrayList<HomeFragmentResponse.Body.Pet>,
    var catArrayList: ArrayList<HomeFragmentResponse.Body.Pet>,
    var clickCallBack: ClickCallBackNew
) : RecyclerView.Adapter<PetNameAdapter.ViewHolder>(), ClickCallBackNew {

    lateinit var context: Context

    private var selectedPos = -1
    var onClickListener: (() -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetNameAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_your_dogs, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetNameAdapter.ViewHolder, position: Int) {

        // check on the conditions if item is selected or not
        if (selectedPos == position) {
            if (selectedPos ==0)
                if (dogArrayList.size>0) {
                    holder.itemView.rvDogsDetails.adapter =
                        DogsAdapter(context, dogArrayList, selectedPos, this)
                  holder.itemView.tvNoData.visibility = View.GONE
                  holder.itemView.addPet.visibility = View.GONE
                    holder.itemView.rvDogsDetails.visibility = View.VISIBLE
                }else{
                    holder.itemView.tvNoData.visibility = View.VISIBLE
                    holder.itemView.addPet.visibility = View.VISIBLE
                    holder.itemView.tvNoData.text = "No Dogs found"
                }
            else if (catArrayList.size>0) {
                    holder.itemView.rvDogsDetails.adapter =
                        DogsAdapter(context, catArrayList, selectedPos, this)
                    holder.itemView.rvDogsDetails.visibility = View.VISIBLE
                    holder.itemView.tvNoData.visibility = View.GONE
                    holder.itemView.addPet.visibility = View.GONE

                }else{
                    holder.itemView.tvNoData.visibility = View.VISIBLE
                    holder.itemView.addPet.visibility = View.VISIBLE
                    holder.itemView.tvNoData.text = "No Cats found"
                }
            holder.itemView.ivCheck.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.itemView.ivCheck.context,
                    R.drawable.ic_dot_select
                )
            )
        } else {
            holder.itemView.rvDogsDetails.visibility = View.GONE
            holder.itemView.ivCheck.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.itemView.ivCheck.context,
                    R.drawable.ic_dot_unselect
                )
            )
        }
        holder.itemView.tvDogName.text = list[position].petName
      Glide.with(context).load(list[position].petImage).into(holder.itemView.ivDog)

        // set click listener on itemview
        holder.itemView.setOnClickListener {
            if (holder.itemView.rvDogsDetails.visibility == View.VISIBLE ||
                holder.itemView.tvNoData.visibility == View.VISIBLE) {
                holder.itemView.rvDogsDetails.visibility = View.GONE
                holder.itemView.tvNoData.visibility = View.GONE
                holder.itemView.addPet.visibility = View.GONE
            }  else {
                selectedPos = position
                notifyDataSetChanged()
            }
        }

        // set in click listner on add pet
        holder.itemView.addPet.setOnClickListener {
            onClickListener?.invoke()

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onItemClick(selectedPos:Int,pos: Int, value: String) {
        clickCallBack.onItemClick(selectedPos,pos, "pet")
    }


}