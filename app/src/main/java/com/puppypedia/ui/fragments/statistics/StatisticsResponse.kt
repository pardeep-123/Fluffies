package com.puppypedia.ui.fragments.statistics


data class StatisticsResponse(
    val success: Boolean, // 1
    val code: Int, // 200
    val msg: String, // get WeightChart successfully 
    val body: List<Body>
) {
    data class Body(
        val id: Int, // 21
        val userid: Int, // 63
        val petid: Int, // 23
        val weight: String, // 12
        val age: String, // 2
        val date: String, // 2021-09-13
        val time: String, // 18:50:40
        val createdAt: String, // 2021-09-16T12:07:57.000Z
        val updatedAt: String // 2021-09-16T12:07:57.000Z
    )
}