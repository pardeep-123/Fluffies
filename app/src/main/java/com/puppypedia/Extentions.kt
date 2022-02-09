package com.puppypedia

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.puppypedia.utils.helper.others.Constants

fun Activity.showToast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

 fun myDeleteDialog(ctx : Context,onClick: ()->Unit,message: String) {
     val dialog = Dialog(ctx)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.window?.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT
    )
    dialog.setCancelable(true)
    dialog.setCanceledOnTouchOutside(false)
    dialog.window?.setGravity(Gravity.CENTER)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setContentView(R.layout.dialog_logout)
    dialog.show()
    dialog.findViewById<TextView>(R.id.tvTitle).text = message
    dialog.findViewById<AppCompatButton>(R.id.btnYes).setOnClickListener {

        dialog.dismiss()
        onClick()
    }
    dialog.findViewById<AppCompatButton>(R.id.btnNo).setOnClickListener {
        dialog.dismiss()

    }
}

/**
 * Method for Opening Images
 */
fun openImagePopUp(pos: String?, ctx: Context) {

    val popup: View
    val layoutInflater: LayoutInflater =
        ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    if (layoutInflater != null) {
        popup = layoutInflater.inflate(R.layout.image_popup, null)
        val popupWindow = PopupWindow(
            popup,
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            true
        )
        popupWindow.showAtLocation(popup, Gravity.CENTER, 0, 0)
        popupWindow.isTouchable = false
        popupWindow.isOutsideTouchable = false
        val headImagePopUp = popup.findViewById<PhotoView>(R.id.headImagePopUp)
        val backpress = popup.findViewById<ImageView>(R.id.backpress)
        backpress.setOnClickListener {
            popupWindow.dismiss()
        }

        Glide.with(ctx).load(Constants.PET_IMAGE_URL + pos).into(headImagePopUp)

    }
}