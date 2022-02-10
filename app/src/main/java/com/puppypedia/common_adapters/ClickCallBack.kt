package com.puppypedia.common_adapters

interface ClickCallBack {
    fun onItemClick(pos: Int, value: String)
}

interface CheckChangeClickCallBack {
    fun onItemClick(pos: Int, value: Boolean)
}