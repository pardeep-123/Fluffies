package com.puppypedia.ui.auth.forgotpassword

import android.app.Activity
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class ForgotPasswordVM: ViewModel() {

    var email: ObservableField<String> = ObservableField("")


    fun onClick(v: View, s: String){

        when(s){


        "btnSubmit" -> {

            (v.context as Activity).onBackPressed()

        }
        }
    }
}