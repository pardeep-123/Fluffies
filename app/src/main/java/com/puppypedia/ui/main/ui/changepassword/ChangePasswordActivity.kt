package com.puppypedia.ui.main.ui.changepassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puppypedia.R
import com.puppypedia.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.tb.tvTitle.text = getString(R.string.change_password)
    }
}