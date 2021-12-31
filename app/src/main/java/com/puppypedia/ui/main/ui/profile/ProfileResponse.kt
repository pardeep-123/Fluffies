package com.puppypedia.ui.main.ui.profile


data class ProfileResponse(
    val success: Boolean, // 1
    val code: Int, // 200
    val msg: String, // Get profile 
    val body: Body
) {
    data class Body(
        val id: Int, // 18
        val name: String, // a
        val email: String, // a@a.a
        val password: String, // 7c4a8d09ca3762af61e59520943dc26494f8941b
        val image: String, // herghref
        val notification: Int, // 1
        val authKey: String, // 2d7b6ee0c2d8b4fe32c8cd8dc51a130a1f1de619
        val deviceType: Int, // 1
        val deviceToken: String, // dsd
        val createdAt: String, // 2021-09-02T06:40:32.000Z
        val updatedAt: String // 2021-09-07T10:22:06.000Z
    )
}