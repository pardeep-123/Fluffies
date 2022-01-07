package com.puppypedia.ui.main.ui.addremainder


import com.google.gson.annotations.SerializedName

//data class AddReminderResponse(
//    val success: Int, // 1
//    val code: Int, // 200
//    val msg: String, // Add Reminder successfully
//    val body: Body
//) {
//    data class Body(
//        val createdAt: CreatedAt,
//        val updatedAt: UpdatedAt,
//        val isReminderSent: Int, // 0
//        val id: Int, // 14
//        val name: String, // pet
//        val time: String, // 08:50:40
//        val date: String, // 2021-09-21
//        val datetime: String, // 2021-09-21 08:50:40
//        val petid: Int, // 11
//        val isremind: Int, // 6
//        val userid: Int // 73
//    ) {
//        data class CreatedAt(
//            @SerializedName("val")
//            val valX: String // CURRENT_TIMESTAMP
//        )
//
//        data class UpdatedAt(
//            @SerializedName("val")
//            val valX: String // CURRENT_TIMESTAMP
//        )
//    }
//}

data class AddReminderResponse(
    @SerializedName("body")
    val body: Body,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("msg")
    val msg: String, // Add Reminder successfully
    @SerializedName("success")
    val success: Boolean // true
) {
    data class Body(
        @SerializedName("createdAt")
        val createdAt: CreatedAt,
        @SerializedName("date")
        val date: String, // 2022/1/7
        @SerializedName("datetime")
        val datetime: String, // 2022/1/7 18:50
        @SerializedName("id")
        val id: Int, // 417
        @SerializedName("isReminderSent")
        val isReminderSent: Int, // 0
        @SerializedName("isremind")
        val isremind: Int, // 1
        @SerializedName("name")
        val name: String, // pershant
        @SerializedName("petid")
        val petid: Int, // 158
        @SerializedName("time")
        val time: String, // 18:50
        @SerializedName("timezone")
        val timezone: String, // Asia/Kolkata
        @SerializedName("updatedAt")
        val updatedAt: UpdatedAt,
        @SerializedName("userid")
        val userid: Int // 184
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