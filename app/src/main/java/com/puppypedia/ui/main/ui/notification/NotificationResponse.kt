package com.puppypedia.ui.main.ui.notification


data class NotificationResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // Notification list get successfully
    val body: List<Any>
)