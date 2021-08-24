package com.puppypedia.ui.main.ui.editprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puppypedia.R
import com.puppypedia.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}