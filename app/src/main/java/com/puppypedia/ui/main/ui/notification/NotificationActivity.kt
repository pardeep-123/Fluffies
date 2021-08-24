package com.puppypedia.ui.main.ui.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.puppypedia.R
import com.puppypedia.common_adapters.NotificationAdapter
import com.puppypedia.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    lateinit var  binding: ActivityNotificationBinding
    private val notificationVM : NotificationVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
           binding.tb.ivBack.setOnClickListener {
                onBackPressed()
            }
        binding.tb.tvTitle.text= getString(R.string.notifications)


        binding.llNoNotifications.setOnClickListener {
           binding. rvNotifications.visibility = View.VISIBLE
            binding.llNoNotifications.visibility = View.GONE
        }
        binding.notificationVM = notificationVM
    }
    fun setAdapter(){
        val notificationAdapter = NotificationAdapter(this)
        binding.rvNotifications.adapter = notificationAdapter
    }
}