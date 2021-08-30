package com.puppypedia.ui.main.ui.changepassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.puppypedia.R
import com.puppypedia.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tb.tvTitle.text = getString(R.string.change_password)
        clicksHandle()
        passwordShowHide()
    }

    private fun clicksHandle() {
        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnSave.setOnClickListener {
            onBackPressed()
        }

    }

    private fun passwordShowHide() {
        binding.cbOldPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                binding.edtOldPass.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                binding.edtOldPass.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        binding.cbNewPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                binding.edtNewPass.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                binding.edtNewPass.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        binding.cbConfirmNewPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                binding.edtConfirmNewPass.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                binding.edtConfirmNewPass.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        }
    }
}