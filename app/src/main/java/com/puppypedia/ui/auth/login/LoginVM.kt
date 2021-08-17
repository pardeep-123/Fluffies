package com.puppypedia.ui.auth.login

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.puppypedia.ui.auth.forgotpassword.ForgotPasswordActivity
import com.puppypedia.ui.auth.signup.SignUpActivity
import com.puppypedia.ui.main.ui.home.HomeActivity

class LoginVM : ViewModel() {

    var email: ObservableField<String> = ObservableField("")
    var password: ObservableField<String> = ObservableField("")

    fun onClick(v : View , s : String ){
        when (s){

            "btnSignIn" -> {

                (v.context as Activity).startActivity(Intent(v.context,HomeActivity::class.java))
        }

            "btnForgot" -> {
                (v.context as Activity).startActivity(Intent(v.context,ForgotPasswordActivity::class.java))


            }
            "SignUp" -> {

                (v.context as Activity).startActivity(Intent(v.context,SignUpActivity::class.java))
            }
    }
    }
}