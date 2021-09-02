package com.puppypedia.restApi

import com.google.gson.annotations.SerializedName

class RestError {
    @SerializedName("code")
    var code: Int = 0

    @SerializedName("msg")
    var msg: String? = null
}