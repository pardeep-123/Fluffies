package com.puppypedia.ui.fragments.home


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HomeFragmentResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // Home data get successfuly
    val body: Body
) : Serializable {
    data class Body(
        @SerializedName("Banners")
        val banners: List<Banner>,
        @SerializedName("Category")
        val category: List<Category>,
        @SerializedName("Pets")
        val pets: List<Pet>,
        val notificationsCount: Int // 0
    ) : Serializable {
        data class Banner(
            val id: Int, // 2
            val description: String, // drgerg
            val image: String // 16501566-be31-4673-9102-395aeddaa92d.jpg
        ) : Serializable

        data class Category(
            val id: Int, // 2
            val name: String, // Toys and Chews
            val image: String,
            val status: Int // 1
        ) : Serializable

        data class Pet(
            val id: Int, // 36
            val name: String, // qqqqqq
            val image: String // /assets/images/pets/1631509343152-file.jpg
        ) : Serializable
    }
}