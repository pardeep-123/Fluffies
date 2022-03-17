package com.fluffies.ui.main.ui.editprofile


data class EditProfileResponse(
    val success: Boolean, // 1
    val code: Int, // 200
    val msg: String, // Profile updated successfully
    val body: Body
) {
    data class Body(
        val id: Int, // 18
        val name: String, // aa
        val email: String, // a@a.a
        val password: String, // 7c4a8d09ca3762af61e59520943dc26494f8941b
        val image: String, // hhhh
        val notification: Int, // 1
        val authKey: String, // 117de861231dfafa48d594352a378c5c5137354d
        val deviceType: Int, // 1
        val deviceToken: String, // dsd
        val createdAt: String, // 2021-09-02T06:40:32.000Z
        val updatedAt: String // 2021-09-07T11:00:14.000Z
    )
}