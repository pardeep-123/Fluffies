package com.puppypedia.ui.main.ui.mypetprofile


data class PetProfileResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // Pets list get successfully
    val body: List<Body>
) {
    data class Body(
        val id: Int, // 17
        val userid: Int, // 13
        val name: String, // rocky
        val gender: Int, // 1
        val age: Int, // 3
        val date: String,
        val time: String,
        val image: String, // d209886d-998f-4efb-b24d-855e0b9baa13.jpg
        val weight: Int, // 111
        val breed: String, // Pitbull
        val about: String, // sdfvs
        val createdAt: String, // 2021-08-17T09:51:18.000Z
        val updatedAt: String // 2021-08-17T09:51:18.000Z
    )
}