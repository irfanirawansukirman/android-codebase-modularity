package com.irfanirawansukirman.abstraction.util.ext

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, message: String?) {
    message?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }
}