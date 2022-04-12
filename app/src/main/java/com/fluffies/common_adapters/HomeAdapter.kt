package com.fluffies.common_adapters

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fluffies.R
import com.fluffies.ui.fragments.home.HomeFragment
import com.fluffies.ui.fragments.home.HomeFragmentResponse
import com.fluffies.utils.helper.others.Constants.Companion.IMAGE_URL
import kotlinx.android.synthetic.main.item_home.view.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class HomeAdapter(
    var context: HomeFragment,
    var datalist: HomeFragmentResponse
) : RecyclerView.Adapter<HomeAdapter.Vh?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return Vh(v)
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        //   holder.itemView.tvDescription.setText(datalist.body.banners[position].description)
        holder.itemView.tvDescription.text = HtmlCompat.fromHtml(
            datalist.body.banners[position].description, HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        holder.itemView.tvDescription.movementMethod = LinkMovementMethod.getInstance()


        Glide.with(context).load("$IMAGE_URL${datalist.body.banners[position].image}")
            .placeholder(R.drawable.dogsimg).into(holder.itemView.details_img)
    }
    override fun getItemCount(): Int {
        return datalist.body.banners.size
    }

    inner class Vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var img: ImageView

    }
}
