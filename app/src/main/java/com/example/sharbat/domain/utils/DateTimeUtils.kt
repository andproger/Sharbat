package com.example.sharbat.domain.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toDateText(): String {
    return SimpleDateFormat("dd MMMM", Locale.getDefault()).format(this);
}

fun Date.toTimeText(): String {
    return SimpleDateFormat("HH:mm", Locale.getDefault()).format(this);
}

fun String.toDate(): Date {
    //return Date()
    val str = this
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(str)!!
}

fun Date.toDateString(): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(this);
}