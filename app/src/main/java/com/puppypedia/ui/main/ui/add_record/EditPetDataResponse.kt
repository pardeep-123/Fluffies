package com.puppypedia.ui.main.ui.add_record


import com.google.gson.annotations.SerializedName

data class EditPetDataResponse(
    val success: Boolean, // true
    val code: Int, // 200
    val msg: String, // pet posts update successfully
    val body: Body
) {
    data class Body(
        val id: Int, // 26
        @SerializedName("user_id")
        val userId: Int, // 190
        val description: String, // string
        @SerializedName("pet_id")
        val petId: Int, // 165
        val createdAt: String, // 2022-01-05T09:51:33.000Z
        val updatedAt: String, // 2022-01-05T09:52:13.000Z
        @SerializedName("pet_images")
        val petImages: List<PetImage>
    ) {
        data class PetImage(
            val id: Int, // 61
            @SerializedName("user_id")
            val userId: Int, // 190
            @SerializedName("pet_id")
            val petId: Int, // 165
            @SerializedName("post_id")
            val postId: Int, // 26
            @SerializedName("pet_image")
            val petImage: String, // fd
            val createdAt: String, // 2022-01-05T09:51:33.000Z
            val updatedAt: String // 2022-01-05T09:51:33.000Z
        )
    }
}