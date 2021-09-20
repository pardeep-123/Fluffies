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
        val notificationsCount: Int // 6
    ) : Serializable {
        data class Banner(
            val id: Int, // 2
            val description: String, // vaccinations will shield our pets from are dangerous, potentially deadly, and, thankfully, mostly preventable.
            val image: String // /assets/images/banners/9acf375e-1610-44fe-ad48-4ef17d8e6adf.jpeg
        ) : Serializable

        data class Category(
            val id: Int, // 2
            val name: String, // Toys and Chews
            val image: String, // /assets/images/categories/d0f4af69-903f-4a52-896e-3065617997e4.png
            val description: String, // <p>qwdasddfasd</p>
            val status: Int // 1
        ) : Serializable

        data class Pet(
            val id: Int, // 36
            val name: String, // qqqqqq
            var selected: Boolean, // /assets/images/pets/1631509343152-file.jpg
            val image: String // /assets/images/pets/1631509343152-file.jpg
        ) : Serializable
    }
}
