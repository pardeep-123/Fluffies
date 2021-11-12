package com.puppypedia.common_adapters

public interface ClickCallBack {
    fun onItemClick(pos: Int, value: String)
}

public interface CheckChangeClickCallBack {
    fun onItemClick(pos: Int, value: Boolean)
}