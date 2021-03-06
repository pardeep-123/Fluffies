package com.fluffies.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.fluffies.R
import com.fluffies.click
import com.fluffies.openImagePopUp
import com.fluffies.utils.helper.others.Constants

class EditLifeAdapter(var ctx : Context,var onImageClick: OnImageClick,var list : ArrayList<String>) : PagerAdapter() {

    interface OnImageClick{
        fun onImageClick(image:String)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val myLayout: View =
            LayoutInflater.from(ctx).inflate(R.layout.rv_multi_images, container, false)
        val img = myLayout.findViewById<ImageView>(R.id.ivImage)
        val iv_delete = myLayout.findViewById<ImageView>(R.id.iv_delete)

        iv_delete.visibility = View.GONE
        img.visibility = View.VISIBLE
        Glide.with(ctx)
            .load(Constants.PET_IMAGE_URL+list[position])
          //  .diskCacheStrategy(DiskCacheStrategy.ALL) // It will cache your image after loaded for first time
            .placeholder(R.drawable.place_holder)
            .into(img)
        container.addView(myLayout, 0)

        // open image
        myLayout.click {
            openImagePopUp(list[position],ctx)
        }
        return myLayout
    }



    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view == o
    }

}