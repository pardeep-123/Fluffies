package com.puppypedia.ui.commomModel


data class CommonModel(
    val success: Boolean, // 0
    val code: Int, // 400
    val msg: Msg,
    val body: Body
) {
    class Msg

    class Body
}

 data class AgeModel(
     var age: String = "",
 var isSelect : Boolean= false
 )