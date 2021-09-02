package com.puppypedia.ui.auth.login


data class LoginResponse(
    val success: Boolean, // true
    val code: Int, // 200
    val message: String, // User log in successfully
    val body: Body
) {
    data class Body(
        val id: Int, // 18
        val name: String, // a
        val email: String, // a@a.a
        val password: String, // 7c4a8d09ca3762af61e59520943dc26494f8941b
        val image: String, // herghref
        val authKey: String, // 20068fa895aa84187f623104582bb1110d57e0f6
        val deviceType: Int, // 1
        val deviceToken: String, // dsd
        val createdAt: String, // 2021-09-02T06:40:32.000Z
        val updatedAt: String // 2021-09-02T09:56:51.000Z
    )
}