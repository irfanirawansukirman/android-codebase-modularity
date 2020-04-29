package com.irfanirawansukirman.abstraction.util.ext

fun CharSequence?.isNotEmpty(): Boolean = this != null && length > 0

fun String.cleanForPhoneNumber(): String {
    return replace(" ", "")
        .replace("+62", "0")
        .replace("-", "")
}