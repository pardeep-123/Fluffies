package com.fluffies.ui.main.ui.petdetail


data class PetDetailResponse(
    val success: Boolean, // 1
    val code: Int, // 200
    val msg: String, // report added successfully
    val body: Body
) {
    data class Body(
        val id: Int, // 25
        val userid: Int, // 18
        val name: String, // qqq
        val gender: Int, // 0
        val age: Int, // 12
        val image: String, // qfdfds
        val weight: Int, // 4
        val breed: String,
        val about: String, // sfddfv
        val createdAt: String, // 2021-09-02T10:50:36.000Z
        val updatedAt: String // 2021-09-02T10:50:36.000Z
    )
}