package com.fluffies.ui.main.ui.editpetprofile


data class EditPetResponse(
    val success: Boolean, // 1
    val code: Int, // 200
    val msg: String, // Edit Pet successfully
    val body: Body
) {
    data class Body(
        val id: Int, // 17
        val userid: Int, // 13
        val name: String, // rocky
        val gender: Int, // 0
        val age: Int, // 0
        val date: String,
        val time: String,
        val image: String, // r
        val weight: Int, // 0
        val breed: String, // ddd
        val about: String, // sddddddddddd
        val createdAt: String, // 2021-08-17T09:51:18.000Z
        val updatedAt: String // 2021-09-10T13:21:03.000Z
    )
}