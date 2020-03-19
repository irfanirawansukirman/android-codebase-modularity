package com.irfanirawansukirman.abstraction.util.ext

import android.content.Intent
import java.net.URLDecoder

fun shareIntentLink(title: String, content: String): Intent {
    val finalShareLink = URLDecoder.decode(content, "UTF-8")

    val sharingIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, finalShareLink)
    }
    return Intent.createChooser(sharingIntent, title)
}