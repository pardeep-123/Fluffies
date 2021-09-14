package com.puppypedia.common_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.puppypedia.R
import com.puppypedia.ui.fragments.home.HomeFragment
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import com.puppypedia.utils.helper.others.Constants
import kotlinx.android.synthetic.main.item_home.view.*


class HomeAdapter(
    var context: HomeFragment,
    var datalist: HomeFragmentResponse
) : RecyclerView.Adapter<HomeAdapter.Vh?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return Vh(v)
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        Glide.with(context).load(Constants.BASE_URL + datalist.body.banners[position].image)
            .placeholder(R.drawable.dogsimg).into(holder.itemView.details_img)

    }

/*    private val itemCount: Int = datalist.size*/

    override fun getItemCount(): Int {
        return datalist.body.banners.size
    }

    inner class Vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*    var img: ImageView
            var videoPic: ImageView? = null

            init {
                img = itemView.findViewById(R.id.details_img)*/
        // videoPic= itemView.findViewById(R.id.iv_videoicon);
    }
}

/*   init {
       this.datalist = datalist
   }*/
