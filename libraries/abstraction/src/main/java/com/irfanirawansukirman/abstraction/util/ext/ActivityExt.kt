package com.irfanirawansukirman.abstraction.util.ext

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.irfanirawansukirman.abstraction.R
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
    overridePendingTransitionEnter()
}

fun AppCompatActivity.makeStatusBarTransparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}

fun AppCompatActivity.overridePendingTransitionEnter() {
    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
}

fun AppCompatActivity.overridePendingTransitionExit() {
    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
}