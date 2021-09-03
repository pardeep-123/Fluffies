package com.puppypedia.ui.main.ui.notification

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.NotificationAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class NotificationActivity : AppCompatActivity(), Observer<RestObservable> {
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = getString(R.string.notifications)


        ll_no_notifications.setOnClickListener {
            rv_notifications.visibility = View.VISIBLE
            ll_no_notifications.visibility = View.GONE
        }
        callApi()
    }

    fun setAdapter() {
        val notificationAdapter = NotificationAdapter(this)
        rv_notifications.adapter = notificationAdapter
    }

    fun callApi() {
        viewModel.getNotificationListAPI(this, true)
        viewModel.mResponse.observe(this, this)
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is NotificationResponse) {
                    val aboutResponse: NotificationResponse = it.data
                    setAdapter()
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