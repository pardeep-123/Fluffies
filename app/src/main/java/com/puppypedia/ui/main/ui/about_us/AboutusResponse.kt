package com.puppypedia.ui.main.ui.about_us


data class AboutusResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // About
    val body: Body
) {
    data class Body(
        val id: Int, // 4
        val name: String, // About
        val value: String, // <p>About</p>
        val createdAt: String, // 2021-08-13T15:54:26.000Z
        val updatedAt: String // 2021-08-13T10:24:52.000Z
    )
}