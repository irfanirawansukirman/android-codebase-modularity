package com.irfanirawansukirman.abstraction.util.ext

import android.app.Activity
import android.view.inputmethod.InputMethodManager

fun showKeyBoard(activity: Activity) {
    val imm =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(
        0,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
}

fun hideKeyBoard(activity: Activity) {
    val imm =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(
        InputMethodManager.HIDE_IMPLICIT_ONLY,
        0
    )
}

fun toggleKeyBoard(activity: Activity) {
    val imm =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm.isActive) {
        hideKeyBoard(activity)
    } else {
        showKeyBoard(activity)
    }
}