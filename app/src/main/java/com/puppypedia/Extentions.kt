package com.puppypedia

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

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