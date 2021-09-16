package com.puppypedia.ui.fragments.weight


data class GetWeightResponse(
    val success: Int, // 1
    val code: Int, // 200
    val msg: String, // Get weight profile 
    val body: Body
) {
    data class Body(
        val id: Int, // 23
        val userid: Int, // 10
        val name: String, // fd
        val gender: Int, // 0
        val age: Int, // 2
        val date: String, // 2021-09-13
        val time: String, // 18:50:40
        val image: String, // dsf
        val weight: Int, // 12
        val breed: String, // dddd
        val about: String, // dddddd
        val createdAt: String, // 2021-08-17T09:56:43.000Z
        val updatedAt: String, // 2021-09-16T12:07:57.000Z
        val weightCharts: List<WeightChart>
    ) {
        data class WeightChart(
            val id: Int, // 6
            val userid: Int, // 22
            val petid: Int, // 23
            val weight: String, // 23
            val age: String, // 4
            val date: String, // 12
            val time: String, // 12-12-1222
            val createdAt: String, // 2021-09-03T09:44:01.000Z
            val updatedAt: String // 2021-09-03T09:44:01.000Z
        )
    }
}