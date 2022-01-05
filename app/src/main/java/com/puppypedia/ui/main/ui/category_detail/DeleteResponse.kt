package com.puppypedia.ui.main.ui.category_detail


data class DeleteResponse(
    val success: Boolean, // true
    val code: Int, // 200
    val msg: String, // pet data deleted successfully
    val body: Body
) {
    class Body
}