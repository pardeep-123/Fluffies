package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import com.puppypedia.databinding.ItemStatusBinding
import de.hdodenhof.circleimageview.CircleImageView

class StatusAdapter(var context: Context, var arrayList: ArrayList<Int>) :
    RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {

        val view = ItemStatusBinding.inflate( LayoutInflater.from(context),parent,false)
//        val view = LayoutInflater.from(context).inflate(R.layout.item_status, parent, false)
        return StatusViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        holder.bind(position)
    }

    /*override fun getItemViewType(position: Int): Int {
        if (arrayList.size-1 == 0){

        }
    }*/

    override fun getItemCount(): Int {
        return arrayList.size
    }

   inner class StatusViewHolder(itemView: ItemStatusBinding) : RecyclerView.ViewHolder(itemView.root) {
        val civPet:CircleImageView = itemView.civPet


        fun bind(pos:Int){
            civPet.setImageResource(arrayList[pos])
        }
    }

}