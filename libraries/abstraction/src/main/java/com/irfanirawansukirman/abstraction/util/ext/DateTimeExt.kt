package com.irfanirawansukirman.abstraction.util.ext

import com.irfanirawansukirman.abstraction.util.Const.DateTime.DATE_TIME_DEFAULT
import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(dateFormat: String): String {
    val simpleDateFormat = SimpleDateFormat(dateFormat, Locale("id", "ID"))
    return simpleDateFormat.format(Date())
}

fun getCurrentTime(timeFormat: String): String {
    val simpleDateFormat = SimpleDateFormat(timeFormat, Locale("id", "ID"))
    return simpleDateFormat.format(Date())
}

fun convertDateToNewFormat(date: String, format: String): String {
    val timeMillis =
        (SimpleDateFormat(DATE_TIME_DEFAULT, Locale("id", "ID")).parse(date) as Date).time
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeMillis

    return SimpleDateFormat(format, Locale("id", "ID")).format(calendar.time)
}