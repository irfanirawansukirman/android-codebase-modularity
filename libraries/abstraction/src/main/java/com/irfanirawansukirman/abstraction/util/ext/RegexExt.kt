package com.irfanirawansukirman.abstraction.util.ext

import java.util.regex.Pattern

fun isEmailValid(email: String): Boolean {
    val emailPattern = Pattern.compile(
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    )
    return emailPattern.matcher(email.trim()).matches()
}

fun isPhoneValid(phone: String): Boolean {
    val phonePattern = Pattern.compile(
        "[+]?[0-9]+"
    )

    return phonePattern.matcher(phone).matches()
}