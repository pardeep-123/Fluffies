package com.fluffies.model
import com.google.gson.annotations.SerializedName


data class AddHealthDetail(
    @SerializedName("body")
    val body: Body,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("msg")
    val msg: String, // pet health added successfully
    @SerializedName("success")
    val success: Boolean // true
) {
    data class Body(
        @SerializedName("createdAt")
        val createdAt: String, // 2022-02-04T05:44:07.617Z
        @SerializedName("description")
        val description: String, // hey
        @SerializedName("id")
        val id: Int, // 15
        @SerializedName("image_1")
        val image1: String, // dfsdffds
        @SerializedName("image_2")
        val image2: String, // dsfdfdsfdsfsdf
        @SerializedName("pet_id")
        val petId: Int, // 125
        @SerializedName("updatedAt")
        val updatedAt: String, // 2022-02-04T05:44:07.617Z
        @SerializedName("user_id")
        val userId: Int // 184
    )
}