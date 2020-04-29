package com.irfanirawansukirman.abstraction.util.ext

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

fun isPhoneValid(phone: String): Boolean {
    val phonePattern = Pattern.compile(
        "[+]?[0-9]+"
    )

    return phonePattern.matcher(phone).matches()
}

fun intentCall(
    phoneNumber: String,
    isDirectCall: Boolean? = true,
    pm: PackageManager,
    context: AppCompatActivity
) {
    isDirectCall?.run {
        val intent = Intent(if (this) Intent.ACTION_CALL else Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${phoneNumber.cleanForPhoneNumber()}")
        }
        if (intent.resolveActivity(pm) != null) context.startActivity(intent)
    }
}