package com.puppypedia.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.puppypedia.R
import com.puppypedia.ui.auth.login.LoginActivity
import com.puppypedia.ui.main.ui.home.HomeActivity
import com.puppypedia.utils.helper.sharedPref.SharedPrefUtil

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({

            if (SharedPrefUtil.getInstance().authToken.isEmpty()) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                startActivity(Intent(this, HomeActivity::class.java))
            }

            finish()
        }, 3000)
    }
}