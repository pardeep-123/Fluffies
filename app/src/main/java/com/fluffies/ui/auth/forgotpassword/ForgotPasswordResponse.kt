package com.fluffies.ui.auth.forgotpassword


data class ForgotPasswordResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // Email sent successfully
    val body: Body
) {
    class Body
}