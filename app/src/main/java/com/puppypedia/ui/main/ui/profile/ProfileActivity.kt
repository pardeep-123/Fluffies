package com.puppypedia.ui.main.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puppypedia.R
import com.puppypedia.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var  binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.tb.tvTitle.text = getString(R.string.profile)
    }
}