package com.puppypedia.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.puppypedia.ui.main.ui.home.HomeActivity

class SignUpVM: ViewModel() {

    var email: ObservableField<String> = ObservableField("")
    var name: ObservableField<String> = ObservableField("")
    var password: ObservableField<String> = ObservableField("")
    var confirmPassword: ObservableField<String> = ObservableField("")

    fun onClick(v : View, s : String){

            when (s){

                "SignIn" -> {
                    (v.context as Activity).finish()
                }

                "btnSignIn" -> {

                    (v.context as Activity).startActivity(Intent(v.context, HomeActivity ::class.java))

                }
            }

    }

}