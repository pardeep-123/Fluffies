package com.puppypedia.common_adapters

abstract class AbstractModel {
    var adapterPosition: Int = -1
    var onItemClick: RecyclerAdapter.OnItemClick? = null
}
