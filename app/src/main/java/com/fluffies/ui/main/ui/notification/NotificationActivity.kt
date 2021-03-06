package com.fluffies.ui.main.ui.notification

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.fluffies.R
import com.fluffies.common_adapters.NotificationAdapter
import com.fluffies.restApi.RestObservable
import com.fluffies.ui.main.ui.AllViewModel
import com.fluffies.utils.helper.others.Helper
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class NotificationActivity : AppCompatActivity(), Observer<RestObservable> {
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    var list: ArrayList<NotificationResponse>? = null
    var aboutResponse: NotificationResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = getString(R.string.notifications)
        rv_notifications.visibility = View.VISIBLE
        ll_no_notifications.visibility = View.GONE

        callApi()
    }


    fun callApi() {
        viewModel.getNotificationListAPI(this, true)
        viewModel.mResponse.observe(this, this)
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is NotificationResponse) {
                    aboutResponse = it.data
                       if (it.data.body.isEmpty()) {
                           ll_no_notifications.visibility = View.VISIBLE
                           rv_notifications.visibility = View.GONE

                       } else {
                           val notificationAdapter = NotificationAdapter(this, aboutResponse!!)
                           rv_notifications.adapter = notificationAdapter
                       }
                }
            }
            it.status == Status.ERROR -> {
                if (it.data != null) {
                    Helper.showErrorAlert(this, it.data as String)
                } else {
                    Helper.showErrorAlert(this, it.error.toString())
                }
            }
        }
    }


}