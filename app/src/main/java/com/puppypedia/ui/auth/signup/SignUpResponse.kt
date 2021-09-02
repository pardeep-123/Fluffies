package com.puppypedia.ui.auth.signup


data class SignUpResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // User registered successfully
    val body: Body
) {
    data class Body(
        val id: Int, // 18
        val name: String, // a
        val email: String, // a@a.a
        val password: String, // 7c4a8d09ca3762af61e59520943dc26494f8941b
        val image: String, // herghref
        val authKey: String, // a61298c3d9bb256914ca6859263d38ee99b9d3b7
        val deviceType: Int, // 1
        val deviceToken: String, // dsd
        val createdAt: String, // 2021-09-02T06:40:32.000Z
        val updatedAt: String // 2021-09-02T06:40:32.000Z
    )
}