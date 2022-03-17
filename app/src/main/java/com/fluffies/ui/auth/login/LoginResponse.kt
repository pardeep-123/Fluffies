package com.fluffies.ui.auth.login

data class LoginResponse(
    val success: Boolean, // true
    val code: Int, // 200
    val message: String, // User log in successfully
    val body: Body
) {
    data class Body(
        val id: Int, // 81
        val name: String, // sonl
        val email: String, // shivu@gmail.com
        val password: String, // 7c4a8d09ca3762af61e59520943dc26494f8941b
        val forgotPasswordHash: String,
        val image: String, // /assets/images/users/1632823899289-file.jpg
        val notification: Int, // 1
        val authKey: String, // 7ffef9093c5d157d55ffe76890e4a6edefe566a5
        val deviceType: Int, // 1
        val deviceToken: String, // 3343636
        val createdAt: String, // 2021-09-28T10:11:39.000Z
        val updatedAt: String, // 2021-10-05T07:44:39.000Z
        val petsCount: Int // 2
    )
}