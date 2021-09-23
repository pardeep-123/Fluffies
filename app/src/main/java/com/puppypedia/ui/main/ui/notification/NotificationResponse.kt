package com.puppypedia.ui.main.ui.notification


data class NotificationResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // Notification list get successfully
    val body: List<Body>
) {
    data class Body(
        val id: Int, // 33
        val isRead: Int, // 1
        val petid: Int, // 23
        val reminderid: Int, // 40
        val message: String, // You have reminder of ddsdsd sfesbnjyjghhbgabvdfr
        val notificationType: Int, // 1
        val receiverId: Int, // 77
        val senderId: Int, // 1
        val createdAt: String, // 2021-09-23T07:10:00.000Z
        val updatedAt: String // 2021-09-23T07:22:24.000Z
    )
}