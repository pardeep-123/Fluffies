package com.fluffies.ui.main.ui.notification


data class NotificationResponse(
    val success: Boolean, // 1
    val code: Int, // 200
    val msg: String, // Notification list get successfully
    val body: List<Body>
) {
    data class Body(
        val id: Int, // 41
        val isRead: Int, // 1
        val petid: Int, // 61
        val reminderid: Int, // 76
        val message: String, // You have reminder of  doggy
        val notificationType: Int, // 1
        val receiverId: Int, // 78
        val senderId: Int, // 1
        val createdAt: String, // 2021-09-24T05:38:00.000Z
        val updatedAt: String, // 2021-09-24T05:55:51.000Z
        val petName: String, // kutta 2
        val petImage: String // /assets/images/pets/1632303979006-file.jpg
    )
}