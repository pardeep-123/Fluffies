package com.puppypedia.ui.main.ui.addremainder


import com.google.gson.annotations.SerializedName

data class AddReminderResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // Add Reminder successfully
    val body: Body
) {
    data class Body(
        val createdAt: CreatedAt,
        val updatedAt: UpdatedAt,
        val isReminderSent: Int, // 0
        val id: Int, // 14
        val name: String, // pet
        val time: String, // 08:50:40
        val date: String, // 2021-09-21
        val datetime: String, // 2021-09-21 08:50:40
        val petid: Int, // 11
        val isremind: Int, // 6
        val userid: Int // 73
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