package com.puppypedia.common_adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import com.puppypedia.ui.main.ui.addhealthproblem.AddHealthDetails

class AddHealthListAdapter : RecyclerView.Adapter<AddHealthListAdapter.ViewHolder>() {
    lateinit var ctx : Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddHealthListAdapter.ViewHolder {
        ctx = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.add_record_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddHealthListAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val intent = Intent(ctx,AddHealthDetails::class.java)
             ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
      return 10
      }

    inner class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}