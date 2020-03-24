package com.irfanirawansukirman.abstraction.util.ext

import android.app.Activity
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener

fun requestSinglePermission(
    permission: String,
    listener: PermissionListener,
    activity: Activity,
    isSameThread: Boolean
) {
    Dexter.withActivity(activity)
        .withPermission(permission)
        .withListener(listener)
        .apply { if (isSameThread) onSameThread() }
        .check()
}

fun requestMultiplePermission(
    permissions: List<String>,
    listener: MultiplePermissionsListener,
    activity: Activity,
    isSameThread: Boolean
) {
    Dexter.withActivity(activity)
        .withPermissions(permissions)
        .withListener(listener)
        .apply { if (isSameThread) onSameThread() }
        .check()
}