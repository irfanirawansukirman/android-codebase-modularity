package com.irfanirawansukirman.abstraction.util.ext

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.karumi.dexter.PermissionToken

fun showAlertDialog(
    context: Context,
    title: String, message: String,
    negativeButton: String,
    positiveButton: String,
    token: PermissionToken? = null
) {
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(
            negativeButton
        ) { dialog, _ ->
            dialog.dismiss()
            token?.cancelPermissionRequest()
        }
        .setPositiveButton(
            positiveButton
        ) { dialog, _ ->
            dialog.dismiss()
            token?.continuePermissionRequest()
        }.show()
}