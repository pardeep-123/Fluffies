package com.fluffies.model

class ServicesModel {
    var img: Int = 0
    var service: String? = null
    var isSelected = false

    constructor()
    constructor(img: Int, service: String, isSelected: Boolean) {

        this.img = img
        this.service = service
        this.isSelected = isSelected
    }
}