package com.irfanirawansukirman.abstraction.util.ext

import android.util.Log

fun logD(TAG: Class<*>, message: String) {
    Log.d(TAG.simpleName, message)
}

fun logE(TAG: Class<*>, message: String) {
    Log.e(TAG.simpleName, message)
}

fun logD(TAG: String, message: String) {
    Log.d(TAG, message)
}

fun logE(TAG: String, message: String) {
    Log.e(TAG, message)
}