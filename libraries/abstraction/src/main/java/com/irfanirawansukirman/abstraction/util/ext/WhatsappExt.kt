package com.irfanirawansukirman.abstraction.util.ext

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity

fun sendDirectMessage(context: AppCompatActivity, targetPhoneNumber: String, message: String) {
    context.apply {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    "https://api.whatsapp.com/send?phone=$targetPhoneNumber&text=$message"
                )
            )
        )
    }
}