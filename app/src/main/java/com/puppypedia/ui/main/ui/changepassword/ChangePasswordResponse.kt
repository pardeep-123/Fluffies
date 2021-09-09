package com.puppypedia.ui.main.ui.changepassword


data class ChangePasswordResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // Password Changed Successfully
    val body: Body
) {
    class Body
}