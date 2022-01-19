package com.puppypedia.ui.fragments.weight
import com.google.gson.annotations.SerializedName



//data class GetWeightResponse(
//    val success: Boolean, // 1
//    val code: Int, // 200
//    val msg: String, // Get weight profile
//    val body: Body
//) {
//    data class Body(
//        val id: Int, // 23
//        val userid: Int, // 10
//        val name: String, // fd
//        val gender: Int, // 0
//        val age: Int, // 2
//        val date: String, // 2021-09-13
//        val time: String, // 18:50:40
//        val image: String, // dsf
//        val weight: Int, // 12
//        val breed: String, // dddd
//        val about: String, // dddddd
//        val createdAt: String, // 2021-08-17T09:56:43.000Z
//        val updatedAt: String, // 2021-09-16T12:07:57.000Z
//        val weightCharts: List<WeightChart>
//    ) {
//        data class WeightChart(
//            val id: Int, // 6
//            val userid: Int, // 22
//            val petid: Int, // 23
//            val weight: String, // 23
//            val age: String, // 4
//            val date: String, // 12
//            val time: String, // 12-12-1222
//            val createdAt: String, // 2021-09-03T09:44:01.000Z
//            val updatedAt: String // 2021-09-03T09:44:01.000Z
//        )
//    }
//}

data class GetWeightResponse(
    @SerializedName("body")
    val body: Body,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("msg")
    val msg: String, // Get weight profile
    @SerializedName("success")
    val success: Boolean // true
) {
    data class Body(
        @SerializedName("about")
        val about: String, // Test this app
        @SerializedName("age")
        val age: Int, // 3
        @SerializedName("breed")
        val breed: String, // MIX
        @SerializedName("createdAt")
        val createdAt: String, // 2022-01-07T04:49:50.000Z
        @SerializedName("date")
        val date: String, // 2022-01-20
        @SerializedName("gender")
        val gender: Int, // 0
        @SerializedName("id")
        val id: Int, // 179
        @SerializedName("image")
        val image: String, // /assets/images/pets/1641530989571-file.jpg
        @SerializedName("name")
        val name: String, // pet2
        @SerializedName("time")
        val time: String, // 06:19
        @SerializedName("updatedAt")
        val updatedAt: String, // 2022-01-19T12:49:22.000Z
        @SerializedName("userid")
        val userid: Int, // 196
        @SerializedName("weight")
        val weight: Int, // 2
        @SerializedName("weightCharts")
        val weightCharts: ArrayList<WeightChart>
    ) {
        data class WeightChart(
            @SerializedName("age")
            val age: String, // 2
            @SerializedName("createdAt")
            val createdAt: String, // 2022-01-18T09:59:04.000Z
            @SerializedName("date")
            val date: String, // 2022-01-19
            @SerializedName("id")
            val id: Int, // 373
            @SerializedName("petid")
            val petid: Int, // 179
            @SerializedName("time")
            val time: String, // 03:28
            @SerializedName("updatedAt")
            val updatedAt: String, // 2022-01-18T09:59:04.000Z
            @SerializedName("userid")
            val userid: Int, // 196
            @SerializedName("weight")
            val weight: String // 4.0
        )
    }
}