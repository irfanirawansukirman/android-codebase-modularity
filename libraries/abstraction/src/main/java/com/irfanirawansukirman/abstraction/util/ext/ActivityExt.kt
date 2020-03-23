package com.irfanirawansukirman.abstraction.util.ext

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.irfanirawansukirman.abstraction.util.Const.Code.REQUEST_CODE

fun AppCompatActivity.navigate(
    targetDeepLink: String,
    intentParams: Intent.() -> Unit
) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(targetDeepLink)
    )
    intent.intentParams()
    startActivityForResult(intent, REQUEST_CODE)
}