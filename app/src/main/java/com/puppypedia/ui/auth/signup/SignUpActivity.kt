package com.puppypedia.ui.auth.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.activity.viewModels
import com.puppypedia.R
import com.puppypedia.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    private  val signUpVM : SignUpVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpVM =  signUpVM

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

        binding.cbConfirmPassPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                binding.edtConfirmPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                binding.edtConfirmPass.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }
}