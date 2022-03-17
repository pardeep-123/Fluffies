package com.fluffies.common_adapters

interface ClickCallBackNew {
    fun onItemClick(selectedPos : Int,pos: Int, value: String)
}
interface ClickCallBack {
    fun onItemClick(pos: Int, value: String)
}
interface CheckChangeClickCallBack {
    fun onItemClick(pos: Int, value: Boolean)
}