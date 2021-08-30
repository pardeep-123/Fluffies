package com.puppypedia.ui.main.ui.editprofile

import android.os.Bundle
import com.puppypedia.R
import com.puppypedia.databinding.ActivityEditProfileBinding
import com.puppypedia.utils.helper.ImagePickerUtility

class EditProfileActivity : ImagePickerUtility() {

    lateinit var binding: ActivityEditProfileBinding

    override fun selectedImage(imagePath: String?, code: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tb.tvTitle.text = getString(R.string.edit_profile)
        clicksHandle()
    }

    private fun clicksHandle() {
        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.rivProfile.setOnClickListener {
            getImage(this, 0)
        }

        binding.btnUpdate.setOnClickListener {
            finish()
        }
    }
}