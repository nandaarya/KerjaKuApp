package com.example.kerjakuapp.utils

import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

fun getCurrentTime(): String {
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return dateFormat.format(Calendar.getInstance().time)
}

fun getCurrentDayOfWeek(): String {
    val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    return dayFormat.format(Calendar.getInstance().time)
}

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return dateFormat.format(Calendar.getInstance().time)
}

fun parseTimeStringToTime(timeString: String): LocalTime {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return LocalTime.parse(timeString, formatter)
}

fun isTimeInRange(time: LocalTime, startTime: LocalTime, endTime: LocalTime): Boolean {
    return !time.isBefore(startTime) && !time.isAfter(endTime)
}