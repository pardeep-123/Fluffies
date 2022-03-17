package com.fluffies.model
import com.google.gson.annotations.SerializedName


data class AddLifeEventModel(
    @SerializedName("body")
    val body: ArrayList<Body>,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("msg")
    val msg: String, // pet event added successfully
    @SerializedName("success")
    val success: Boolean // true
) {
    data class Body(
        @SerializedName("createdAt")
        val createdAt: String, // 2022-02-08T10:15:53.000Z
        @SerializedName("date")
        val date: String, // 01-09-1998
        @SerializedName("description")
        val description: String, // sdgagasgsadgtring
        @SerializedName("id")
        val id: Int, // 12
        @SerializedName("pet_id")
        val petId: Int, // 158
        @SerializedName("pet_images")
        val petImages: List<PetImage>,
        @SerializedName("title")
        val title: String, // string1
        @SerializedName("updatedAt")
        val updatedAt: String, // 2022-02-08T10:15:53.000Z
        @SerializedName("user_id")
        val userId: Int // 184
    ) {
        data class PetImage(
            @SerializedName("createdAt")
            val createdAt: String, // 2022-02-08T10:15:53.000Z
            @SerializedName("event_id")
            val eventId: Int, // 12
            @SerializedName("id")
            val id: Int, // 77
            @SerializedName("pet_id")
            val petId: Int, // 158
            @SerializedName("pet_image")
            val petImage: String, // string1
            @SerializedName("post_id")
            val postId: Int, // 0
            @SerializedName("type")
            val type: Int, // 2
            @SerializedName("updatedAt")
            val updatedAt: String, // 2022-02-08T10:15:53.000Z
            @SerializedName("user_id")
            val userId: Int // 184
        )
    }
}