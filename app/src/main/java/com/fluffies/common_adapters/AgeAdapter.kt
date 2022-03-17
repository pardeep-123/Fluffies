package com.fluffies.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fluffies.R
import com.fluffies.ui.commomModel.AgeModel

class AgeAdapter(var list : ArrayList<AgeModel>,var onAgeClick: OnAgeClick) : RecyclerView.Adapter<AgeAdapter.ViewHolder>() {

    var position = -1
    lateinit var context : Context
    interface OnAgeClick{
        fun onAge(s: String)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgeAdapter.ViewHolder {
       context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_age, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgeAdapter.ViewHolder, position: Int) {

        if (list[position].isSelect )
            holder.ageText.setTextColor(context.getColor(R.color.colorPrimary))
        else
            holder.ageText.setTextColor(context.getColor(R.color.black))

        holder.ageText.text = list[position].age

        holder.itemView.setOnClickListener {
            onAgeClick.onAge(list[position].age.split(" ")[1])
            list.forEachIndexed { index, ageModel ->
                if (position == index){
                    ageModel.isSelect = true
                }else
                    ageModel.isSelect = false
            }
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
    return  list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val ageText = itemView.findViewById<TextView>(R.id.ageText)
    }
}