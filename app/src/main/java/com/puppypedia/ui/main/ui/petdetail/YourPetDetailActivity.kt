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
import com.puppypedia.databinding.ActivityYourPetDetailBinding
import com.puppypedia.ui.main.ui.home.HomeActivity


class YourPetDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityYourPetDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourPetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.tb.ivBack.setOnClickListener {
                onBackPressed()
            }

       binding.tb.tvTitle .text = getString(R.string.your_pet_detail)

        binding.btnPetDetail.setOnClickListener {
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