package com.irfanirawansukirman.abstraction.util.ext

import android.content.Intent
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity

fun intentWirelessSettings(context: AppCompatActivity) {
    context.apply {
        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}