package com.fluffies.model
import com.google.gson.annotations.SerializedName


data class GetImageModel(
    @SerializedName("body")
    val body: ArrayList<Body>,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("msg")
    val msg: String, // picture list
    @SerializedName("success")
    val success: Boolean // true
) {
    data class Body(
        @SerializedName("createdAt")
        val createdAt: String, // 2022-02-08T07:31:29.000Z
        @SerializedName("event_id")
        val eventId: Int, // 0
        @SerializedName("id")
        val id: Int, // 82
        @SerializedName("pet_id")
        val petId: Int, // 164
        @SerializedName("pet_image")
        val petImage: String, // /assets/images/pets/1644305471029-file.jpg
        @SerializedName("post_id")
        val postId: Int, // 0
        @SerializedName("type")
        val type: Int, // 1
        @SerializedName("updatedAt")
        val updatedAt: String, // 2022-02-08T07:31:29.000Z
        @SerializedName("user_id")
        val userId: Int // 187
    )


}