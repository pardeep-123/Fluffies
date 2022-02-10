package com.puppypedia.ui.main.ui.category_detail
import com.google.gson.annotations.SerializedName



data class DeleteResponse(
    val success: Boolean, // true
    val code: Int, // 200
    val msg: String, // pet data deleted successfully
    val body: Body
) {
    class Body
}

data class BackgroundModel(
    @SerializedName("body")
    val body: Body,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("msg")
    val msg: String, // background image
    @SerializedName("success")
    val success: Boolean // true
) {
    data class Body(
        @SerializedName("background_image")
        val backgroundImage: String // http://app.puppypediaapp.com/assets/images/admin/f187db92-1f46-48b0-9ca2-e9ea2952c967.png
    )
}