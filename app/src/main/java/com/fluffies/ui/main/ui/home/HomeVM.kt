package com.fluffies.ui.main.ui.home

import android.view.View
import androidx.lifecycle.ViewModel

class HomeVM : ViewModel() {

    fun onClick(v : View, S : String){

            when(S){
                "btnNotification" ->{

                  //  (v.context as Activity).startActivity(Intent( v. context, NotificationActivity:: class.java))
                }
            }
    }
}