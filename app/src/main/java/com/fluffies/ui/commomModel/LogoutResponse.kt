package com.fluffies.ui.commomModel


data class LogoutResponse(
    val success: Boolean, // 1
    val code: Int, // 200
    val msg: String, // Logout successfully
    val body: Body
) {
    data class Body(
        val id: Int, // 44
        val name: String, // mahesh
        val email: String, // mj@gmail.com
        val password: String, // 3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d
        val image: String, // zczzxcxzcxzc
        val notification: Int, // 1
        val authKey: String,
        val deviceType: Int, // 1
        val deviceToken: String,
        val createdAt: String, // 2021-09-08T06:13:17.000Z
        val updatedAt: String // 2021-09-08T12:00:19.000Z
    )
}