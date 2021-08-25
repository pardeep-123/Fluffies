package com.puppypedia.utils.helper

import java.text.SimpleDateFormat
import java.util.*

class AppUtils {
    companion object {

        //--------------------------Date ----------------------//
        fun dateInString(timeInMillis: Long, format: String): String {
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            return sdf.format(timeInMillis)
        }
    }
}