package com.puppypedia.ui.auth.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.puppypedia.R
import com.puppypedia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val loginVM : LoginVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginVM = loginVM

        passwordShowHide()
    }

    private fun passwordShowHide() {
        binding.cbPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                binding.edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                binding.edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }
}