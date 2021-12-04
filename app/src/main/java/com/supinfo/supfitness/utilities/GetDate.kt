package com.supinfo.supfitness.utilities

import java.text.SimpleDateFormat
import java.util.*


class GetDate {

    // Format YYYY:MM:DD HH:MM:SS

    fun getCurrentDateTime(): String {
        var currentDate = Calendar.getInstance().time

        fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }

        return currentDate.toString("yyyy/MM/dd HH:mm:ss")
    }

}