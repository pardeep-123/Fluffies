package com.puppypedia.ui.main.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.puppypedia.R
import com.puppypedia.base.ImagePickerUtilityNew
import kotlinx.android.synthetic.main.activity_add_record.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*
import java.io.File

class AddRecordActivity : ImagePickerUtilityNew() {



    override fun selectedImage(imagePath: File?)
    {

        Glide.with(this).load(imagePath).into(ivAddPost)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        tb_add.tv_title.setTextColor(getColor(R.color.black))
        tb_add.iv_back.setImageResource(R.drawable.back_arrow)
        tb_add.iv_back.setOnClickListener {
            onBackPressed()
        }
        if (intent.getStringExtra("from")=="add"){
            tb_add.tv_title.text = "Add Record"


        }
        else{
            tb_add.tv_title.text = "Edit Record"

        }

        ivAddPost.setOnClickListener {
            getImage(this, 0, false)
        }
    }
}