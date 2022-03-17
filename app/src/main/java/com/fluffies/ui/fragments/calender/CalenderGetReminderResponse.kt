package com.fluffies.ui.fragments.calender


data class CalenderGetReminderResponse(
    val success: Boolean, // 1
    val code: Int, // 200
    val msg: String, // get All reminders successfully
    val body: List<Body>
) {
    data class Body(
        val id: Int, // 49
        val userid: Int, // 77
        val petid: Int, // 23
        val isremind: Int, // 1
        val isReminderSent: Int, // 0
        val name: String, // qqq
        val datetime: String, // 2021-09-23 13:02:00
        val date: String, // 2021-09-23
        val time: String, // 13:02:00
        val createdAt: String, // 2021-09-23T07:48:02.000Z
        val updatedAt: String, // 2021-09-23T07:48:02.000Z
        val petName: String // qqqq
    )
}