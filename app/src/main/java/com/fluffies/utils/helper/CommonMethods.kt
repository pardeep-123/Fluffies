package com.fluffies.utils.helper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.format.DateFormat
import android.util.Log
import android.view.MotionEvent
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.fluffies.R
import com.fluffies.utils.helper.others.Constants
import java.text.SimpleDateFormat
import java.util.*

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


    fun timeStampToDate(timestamp: Int): String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = timestamp * 1000L
        return DateFormat.format("yyyy-MM-dd", calendar).toString()
    }

    fun dateToTimestamp(date: String): Long {
        val formatter: java.text.DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val getDate = formatter.parse(date) as Date
        val output = getDate.time
        return output
    }

    fun dateToTimestampReminder(date: String): Long {
        val formatter: java.text.DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val getDate = formatter.parse(date) as Date
        val output = getDate.time
        return output
    }
//    2022-01-24 13:55

    fun convertOnedatetoAnother(date: String, olddateformt: String, newdateformat: String): Long {
        val formatter: java.text.DateFormat = SimpleDateFormat(newdateformat)
        val getDate = formatter.parse(date) as Date
        val output = getDate.time
        return output
    }

    fun Context.vectorToBitmap(drawableId: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(this, drawableId) ?: return null
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        ) ?: return null
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

}