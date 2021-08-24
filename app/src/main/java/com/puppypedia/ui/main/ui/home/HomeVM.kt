package com.puppypedia.ui.main.ui.home

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.puppypedia.ui.main.ui.notification.NotificationActivity

class HomeVM : ViewModel() {

    fun onClick(v : View, S : String){

            when(S){
                "btnNotification" ->{

                  //  (v.context as Activity).startActivity(Intent( v. context, NotificationActivity:: class.java))
                }
            }
    }
}