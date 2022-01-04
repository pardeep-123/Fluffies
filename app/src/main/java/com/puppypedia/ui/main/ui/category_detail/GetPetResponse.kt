package com.puppypedia.ui.main.ui.category_detail


import com.google.gson.annotations.SerializedName

data class GetPetResponse(
    val success: Boolean, // true
    val code: Int, // 200
    val msg: String, // pet posts
    val body: List<Body>
) {
    data class Body(
        val id: Int, // 6
        @SerializedName("user_id")
        val userId: Int, // 187
        val description: String, // dsaxz
        @SerializedName("pet_id")
        val petId: Int, // 161
        val createdAt: String, // 2022-01-04T12:38:04.000Z
        val updatedAt: String, // 2022-01-04T12:38:04.000Z
        @SerializedName("pet_images")
        val petImages: List<PetImage>
    ) {
        data class PetImage(
            val id: Int, // 34
            @SerializedName("user_id")
            val userId: Int, // 187
            @SerializedName("pet_id")
            val petId: Int, // 161
            @SerializedName("post_id")
            val postId: Int, // 6
            @SerializedName("pet_image")
            val petImage: String, // fdfd
            val createdAt: String, // 2022-01-04T12:38:04.000Z
            val updatedAt: String // 2022-01-04T12:38:04.000Z
        )
    }
}