package com.puppypedia.model


import com.google.gson.annotations.SerializedName

data class NewAddModel(
    @SerializedName("body")
    val body: Body,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("msg")
    val msg: String, // pet image added successfully
    @SerializedName("success")
    val success: Boolean // true
) {
    data class Body(
        @SerializedName("createdAt")
        val createdAt: String, // 2022-02-09T12:53:53.810Z
        @SerializedName("event_id")
        val eventId: Int, // 0
        @SerializedName("id")
        val id: Int, // 129
        @SerializedName("pet_id")
        val petId: String, // 164
        @SerializedName("pet_image")
        val petImage: String, // /assets/images/pets/1644411233449-file.jpg
        @SerializedName("post_id")
        val postId: Int, // 0
        @SerializedName("type")
        val type: Int, // 1
        @SerializedName("updatedAt")
        val updatedAt: String, // 2022-02-09T12:53:53.810Z
        @SerializedName("user_id")
        val userId: Int // 187
    )
}