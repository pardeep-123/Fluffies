package com.puppypedia.ui.main.ui.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.puppypedia.R
import com.puppypedia.common_adapters.NotificationAdapter
import com.puppypedia.databinding.ActivityNotificationBinding
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.auth_toolbar.*

class NotificationActivity : AppCompatActivity() {

    lateinit var  binding: ActivityNotificationBinding
    private val notificationVM : NotificationVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
            iv_back.setOnClickListener {
                onBackPressed()
            }
        tv_title.text= getString(R.string.notifications)


        ll_no_notifications.setOnClickListener {
            rv_notifications.visibility = View.VISIBLE
            ll_no_notifications.visibility = View.GONE
        }
        binding.notificationVM = notificationVM
    }
    fun setAdapter(){
        val notificationAdapter = NotificationAdapter(this)
        rv_notifications.adapter = notificationAdapter
    }
}