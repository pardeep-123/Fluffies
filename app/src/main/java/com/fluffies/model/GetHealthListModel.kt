package com.fluffies.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class GetHealthListModel(
    @SerializedName("body")
    val body: ArrayList<Body>,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("msg")
    val msg: String, // health data list
    @SerializedName("success")
    val success: Boolean // true
) {
    @Parcelize
    data class Body(
        @SerializedName("createdAt")
        val createdAt: String, // 2022-02-03T09:50:26.000Z
        @SerializedName("description")
        val description: String, // fdghdfghdfgh
        @SerializedName("id")
        val id: Int, // 6
        @SerializedName("image_1")
        val image1: String, // gsrgsdfgdsfgsdfg
        @SerializedName("image_2")
        val image2: String, // fgdjdfghdfghdfgh
        @SerializedName("pet_id")
        val petId: Int, // 500
        @SerializedName("petName")
        val petName: String,
        @SerializedName("updatedAt")
        val updatedAt: String, // 2022-02-03T09:50:26.000Z
        @SerializedName("user_id")
        val userId: Int // 184
    ) : Parcelable
}