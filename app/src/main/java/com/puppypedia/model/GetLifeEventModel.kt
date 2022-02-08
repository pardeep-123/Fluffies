package com.puppypedia.model
import com.google.gson.annotations.SerializedName


data class GetLifeEventModel(
    @SerializedName("body")
    val body: ArrayList<Body>,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("msg")
    val msg: String, // pet event
    @SerializedName("success")
    val success: Boolean // true
) {
    data class Body(
        @SerializedName("createdAt")
        val createdAt: String, // 2022-02-07T11:54:35.000Z
        @SerializedName("date")
        val date: String, // 01-19-1998
        @SerializedName("description")
        val description: String, // sadfasdfasfd
        @SerializedName("id")
        val id: Int, // 9
        @SerializedName("pet_id")
        val petId: Int, // 158
        @SerializedName("pet_images")
        val petImages: ArrayList<PetImage>,
        @SerializedName("title")
        val title: String, // my new event
        @SerializedName("updatedAt")
        val updatedAt: String, // 2022-02-07T11:54:35.000Z
        @SerializedName("user_id")
        val userId: Int // 184
    ) {
        data class PetImage(
            @SerializedName("createdAt")
            val createdAt: String, // 2022-02-07T11:54:35.000Z
            @SerializedName("event_id")
            val eventId: Int, // 9
            @SerializedName("id")
            val id: Int, // 71
            @SerializedName("pet_id")
            val petId: Int, // 158
            @SerializedName("pet_image")
            val petImage: String, // string2
            @SerializedName("post_id")
            val postId: Int, // 0
            @SerializedName("type")
            val type: Int, // 2
            @SerializedName("updatedAt")
            val updatedAt: String, // 2022-02-07T11:54:35.000Z
            @SerializedName("user_id")
            val userId: Int // 184
        )
    }
}