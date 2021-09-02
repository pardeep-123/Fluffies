package com.puppypedia.ui.auth.forgotpassword

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.puppypedia.R
import kotlinx.android.synthetic.main.activity_forgot_password.*


class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btnSubmit.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSubmit -> {
                finish()
            }
        }
    }
}