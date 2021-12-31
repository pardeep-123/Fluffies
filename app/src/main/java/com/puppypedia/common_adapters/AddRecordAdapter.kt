package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import java.util.ArrayList

class AddRecordAdapter(var mContext1: Context) :
    RecyclerView.Adapter<AddRecordAdapter.ViewHolderGrid>() {
    private lateinit var mContext: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGrid {
        mContext1 = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_record_layout, parent, false)

        return ViewHolderGrid(view)
    }

    override fun onBindViewHolder(holder: ViewHolderGrid, position: Int) {


    }

    override fun getItemCount(): Int {
        return 5
    }

    class ViewHolderGrid(item1: View) : RecyclerView.ViewHolder(item1) {


        val iv_record: ImageView = item1.findViewById(R.id.iv_record)
        val tv_record: TextView = item1.findViewById(R.id.tv_record)


    }
//    private fun imageWithLoader(imView: ImageView, url: String, progressBar: ProgressBar) {
//        Glide.with(mContext)
//            .load(url)
//            .placeholder(R.drawable.ic_image_placeholder)
//            .error(R.drawable.ic_image_placeholder)
//            .listener(object : RequestListener<Drawable?> {
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: com.bumptech.glide.request.target.Target<Drawable?>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//
//                    progressBar.visibility = View.GONE
//                    return false
//                }
//
//                override fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: com.bumptech.glide.request.target.Target<Drawable?>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    progressBar.visibility = View.GONE
//                    return false
//                }
//            })
//            .into(imView)
//    }


}
