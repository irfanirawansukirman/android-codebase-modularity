package com.irfanirawansukirman.abstraction.util.ext

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(@StringRes message: Int, rootView: View) =
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()

fun showSnackbar(message: String, rootView: View) =
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()