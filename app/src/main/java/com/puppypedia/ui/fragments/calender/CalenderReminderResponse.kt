package com.puppypedia.ui.fragments.calender


data class CalenderReminderResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // get All reminders successfully 
    val body: List<Any>
)