package com.puppypedia.ui.main.ui.petdetail

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import com.puppypedia.R
import com.puppypedia.ui.main.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_your_pet_detail.*
import kotlinx.android.synthetic.main.auth_toolbar.*

class YourPetDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_pet_detail)

            iv_back.setOnClickListener {
                onBackPressed()
            }

        tv_title.text = getString(R.string.your_pet_detail)

        btnPetDetail.setOnClickListener {
            dialogAddPet()

        }
    }

    fun dialogAddPet(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add_new_pet)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setGravity(Gravity.CENTER)
        dialog.show()

        val btnAddNew = dialog.findViewById<Button>(R.id.Add)
        val btnLater = dialog.findViewById<Button>(R.id.later)

        btnAddNew.setOnClickListener {
            dialog.dismiss()
        }
        btnLater.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
            dialog.dismiss()
        }
    }
}