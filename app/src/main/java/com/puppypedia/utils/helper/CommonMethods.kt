package com.puppypedia.utils.helper

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.MotionEvent
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.puppypedia.R
import com.puppypedia.utils.helper.others.Constants

object CommonMethods {
    @SuppressLint("ClickableViewAccessibility")
    fun scrollEditText(editText: EditText) {
        editText.setOnTouchListener { v, event ->
            if (editText.hasFocus()) {
                v.parent.requestDisallowInterceptTouchEvent(true)
            }
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_SCROLL -> {
                    v.parent.requestDisallowInterceptTouchEvent(false)
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
    }


    fun setImage(ivImage: ImageView, str: String) {
        try {
            Glide.with(ivImage.context)
                .asBitmap().load(Constants.IMAGE_URL + str)
                .apply(RequestOptions().override(600, 200))
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        ivImage.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        Log.e("Image", "LoadingFailed")
                        ivImage.setImageResource(R.drawable.place_holder)
                    }

                    override fun onDestroy() {
                        Log.e("Image", "destroyed")
                        ivImage.setImageResource(R.drawable.place_holder)
                    }
                })


            /*var img: String = CommonKeys.BASE_IMAGE_PROFILE + str
            Glide.with(ivImage.context).load(CommonKeys.BASE_IMAGE_PROFILE + str).placeholder(
                ContextCompat.getDrawable(
                    ivImage.context,
                    R.drawable.placeholder
                )
            ).into(ivImage)*/
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}