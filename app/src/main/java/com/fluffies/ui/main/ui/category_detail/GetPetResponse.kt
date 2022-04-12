package com.fluffies.ui.main.ui.category_detail


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GetPetResponse(
    val success: Boolean, // true
    val code: Int, // 200
    val msg: String, // pet posts
    val body: ArrayList<Body>
) : Serializable {
    data class Body(
        val id: Int?=null, // 6
        @SerializedName("user_id")
        val userId: Int?=null, // 187
        var description: String?=null, // dsaxz
        @SerializedName("pet_id")
        val petId: Int?=null, // 161
        val createdAt: String?=null, // 2022-01-04T12:38:04.000Z
        val updatedAt: String?=null, // 2022-01-04T12:38:04.000Z
        @SerializedName("pet_images")
        val petImages: ArrayList<PetImage>?=null
    ) : Serializable {
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
        ) : Serializable
    }
}