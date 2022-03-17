package com.fluffies.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fluffies.R
import com.fluffies.ui.fragments.weight.GetWeightResponse
import kotlinx.android.synthetic.main.item_weight.view.*

class WeightAdapter(var context: Context, var weightlist: ArrayList<GetWeightResponse.Body.WeightChart>,
                    var onDeleteClick: OnDeleteClick,var from :String) :

    RecyclerView.Adapter<WeightAdapter.WeightViewHolder>() {
    // create interface
    interface OnDeleteClick{
        fun onDeleteClick(postId:String,petId:String){}
    }
    var onItemClick: ((pos: Int) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeightViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_weight, parent, false)
        return WeightViewHolder(v)
    }

    override fun onBindViewHolder(holder: WeightAdapter.WeightViewHolder, position: Int) {
        if (from == "stat")
            holder.itemView.ivDelete.visibility = View.GONE
        else
            holder.itemView.ivDelete.visibility = View.VISIBLE
        weightlist[position].run {
            holder.itemView.tvWeight.text = weight + " lbs"
            if (age.contains("Yr")|| age.contains("yr"))
            holder.itemView.tvAge.text = age
            else
                holder.itemView.tvAge.text = age + " yr"
            holder.itemView.tvDate.text = date
            holder.itemView.tvTime.text = time
        }
        holder.itemView.ivDelete.setOnClickListener {
            onDeleteClick.onDeleteClick(weightlist[position].id.toString(),
                          weightlist[position].petid.toString())
        }
      //  holder.itemView.swipeLayout.close(true)
    }

    override fun getItemCount(): Int {
        return weightlist.size
    }

    inner class WeightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var heightInitial = 0

//        init {
//            itemView.swipeLayout?.post {
//                heightInitial = itemView.swipeLayout.layoutParams.height
//                itemView.llDelete.layoutParams.height = heightInitial
//            }
//        }
        /*    RecyclerView.ViewHolder(binding.root) {
            val tvWeight = binding.tvWeight
            val tvAge = binding.tvAge
            val tvDate = binding.tvDate
            val tvTime = binding.tvTime
            fun bind(pos: Int) {}*/
    }
}