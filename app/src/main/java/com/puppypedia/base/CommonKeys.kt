package com.puppypedia.base

import androidx.databinding.ObservableField

object CommonKeys {

    const val URL = ""
    const val BASE_URL = URL + ""
    const val Socket_Base_Url = ""
    const val Socket_Image_Url = ""
    const val BASE_IMAGE_PROFILE = ""

    //preferences
    const val DEVICE_ID = "deviceID"
    const val FIREBASE_FCM_TOKEN = "firebase_fcm_token"
    const val preferenceName = "BuzzAmaid"
    const val foreverPreferenceName = "BuzzAmaidForever"
    const val loginData = "loginData"
    const val notifiactionBroadcase = "notificationCallChange"
    var notifyScreen = ""
    var anotherUserId = ""

    var currentLat: ObservableField<Double> = ObservableField(0.0)
    var currentLng: ObservableField<Double> = ObservableField(0.0)


}
