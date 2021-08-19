package com.puppypedia.ui.main.ui.notification

import androidx.lifecycle.ViewModel
import com.puppypedia.R
import com.puppypedia.common_adapters.RecyclerAdapter
import com.puppypedia.model.NotificationModel

class NotificationVM : ViewModel() {

    val adapterNotification by lazy { RecyclerAdapter<NotificationModel>(R.layout.item_notification) }


}