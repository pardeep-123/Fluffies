package com.fluffies.ui.commomModel


data class NotificationOnOffModel(
    val success: Boolean, // 1
    val code: Int, // 200
    val msg: String, // Notification status update successfully
    val body: Body
) {
    data class Body(
        val id: Int, // 39
        val name: String, // hnn
        val email: String, // ma@m.m
        val password: String, // 7c4a8d09ca3762af61e59520943dc26494f8941b
        val image: String, // zczzxcxzcxzc
        val notification: Int, // 0
        val authKey: String, // 5706ed6fc1d621a7bc4580715e71ffa7cc48f60c
        val deviceType: Int, // 1
        val deviceToken: String, // z
        val createdAt: String, // 2021-09-03T12:02:50.000Z
        val updatedAt: String // 2021-09-03T12:35:35.000Z
    )
}