package com.puppypedia.ui.main.ui.add_weight


import com.google.gson.annotations.SerializedName

data class AddWeightResponse(
    val success: Boolean, // 1
    val code: Int, // 200
    val msg: String, // Add weight chart successfully
    val body: Body
) {
    data class Body(
        val createdAt: CreatedAt,
        val updatedAt: UpdatedAt,
        val id: Int, // 21
        val userid: Int, // 63
        val petid: Int, // 23
        val age: String, // 2
        val weight: String, // 12
        val time: String, // 18:50:40
        val date: String // 2021-09-13
    ) {
        data class CreatedAt(
            @SerializedName("val")
            val valX: String // CURRENT_TIMESTAMP
        )

        data class UpdatedAt(
            @SerializedName("val")
            val valX: String // CURRENT_TIMESTAMP
        )
    }
}