package com.puppypedia.ui.commomModel


data class ImageUploadResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // Successufully
    val body: List<Body>
) {
    data class Body(
        val image: String, // 1631082269459-file.png
        val fileName: String // Screenshot from 2021-08-16 10-11-40.png
    )
}