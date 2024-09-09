package com.example.newspaper.extensions

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.regex.Pattern

val String.Companion.EMPTY: String
    get() = ""

fun String.isEmailValid(): Boolean {
    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    return emailPattern.matcher(this).matches()
}

fun String.formatAsReadableDate(): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(this)

        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm", Locale("tr"))

        zonedDateTime.format(formatter)
    } catch (e: Exception) {
        this
    }
}