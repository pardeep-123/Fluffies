package com.puppypedia.common_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.puppypedia.R
import com.yanzhenjie.album.AlbumFile


class PetsImageAdapter(
    var context: Context,
    mAlbumFiles: ArrayList<AlbumFile>,
    s: String,
    nothing: Nothing?
) : RecyclerView.Adapter<PetsImageAdapter.Vh?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_multi_images, parent, false)
        return Vh(v)
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {

    }

    override fun getItemCount(): Int {
        return 2
    }

    inner class Vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var img: ImageView

    }
}
