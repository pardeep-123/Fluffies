package com.puppypedia.ui.fragments.calender


data class ReminderOnOffResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // Reminder status update successfully
    val body: Body
) {
    data class Body(
        val id: Int, // 69
        val userid: Int, // 78
        val petid: Int, // 64
        val isremind: Int, // 1
        val isReminderSent: Int, // 0
        val name: String, // sfghj
        val datetime: String, // 2021-09-23 06:38
        val date: String, // 2021-09-23
        val time: String, // 06:38
        val createdAt: String, // 2021-09-23T12:58:01.000Z
        val updatedAt: String // 2021-09-23T13:05:11.000Z
    )
}