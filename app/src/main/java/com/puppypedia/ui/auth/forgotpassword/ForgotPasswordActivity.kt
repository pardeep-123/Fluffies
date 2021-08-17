package com.puppypedia.ui.auth.forgotpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.puppypedia.R
import com.puppypedia.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var  binding: ActivityForgotPasswordBinding
    private  val forgotPasswordVM : ForgotPasswordVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.forgotVM = forgotPasswordVM

    }
}