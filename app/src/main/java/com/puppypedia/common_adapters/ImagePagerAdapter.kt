package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.puppypedia.R
import com.puppypedia.ui.main.ui.category_detail.GetPetResponse
import com.puppypedia.utils.helper.others.Constants
/**
 * @author PArdeep Sharma
 *  create pager adapter to show images with view pager
 *  created on 07/01/2022
 */
class ImagePagerAdapter  (
    var ctx: Context,
    var list: ArrayList<GetPetResponse.Body.PetImage>,
    var sendClick: ImageAdapter.SendClick, var from : String,var onOpenImage: OnOpenImage
    ) : PagerAdapter() {

    interface OnOpenImage{
        fun onClick(path:String)
    }
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val myLayout: View =
                LayoutInflater.from(ctx).inflate(R.layout.rv_multi_images, container, false)
            val img = myLayout.findViewById<ImageView>(R.id.ivImage)
            val ivDelete = myLayout.findViewById<ImageView>(R.id.iv_delete)
            val layoUt = myLayout.findViewById<RelativeLayout>(R.id.multiLayout)
            // check if adapter call from Add Record or Category Details
            if (from == "record") {
                ivDelete.visibility = View.VISIBLE
            }else{
                ivDelete.visibility = View.GONE
                val params = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )
                layoUt.layoutParams = params

            }
            Glide.with(ctx)
                .load(Constants.PET_IMAGE_URL + list[position].petImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // It will cache your image after loaded for first time
                .placeholder(R.drawable.puppypediamain)
                .into(img)

            ivDelete.setOnClickListener {
                // to check if size is greater than 1 then api will be hit otherwise show error
                if (list.size>1) {
                    sendClick.onClick(
                        list[position].postId.toString(),
                        list[position].id.toString()
                    )
                    list.remove(list[position])

                    notifyDataSetChanged()
                } else{
                    Toast.makeText(ctx,"You have to remain atleast One Photo", Toast.LENGTH_LONG).show()
                }
            }
            myLayout.setOnClickListener {
               onOpenImage.onClick(list[position].petImage)
            }

            container.addView(myLayout, 0)
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