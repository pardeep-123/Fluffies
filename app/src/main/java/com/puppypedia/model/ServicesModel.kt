package com.puppypedia.model

import android.widget.TextView

class ServicesModel{
    var img : Int= 0
    var service : String ? = null

    constructor()
    constructor(img :Int ,service : String){

            this.img =img
                this.service = service
    }
}