package com.irfanirawansukirman.abstraction.util.ext

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.irfanirawansukirman.abstraction.R
import com.irfanirawansukirman.abstraction.util.Const.Code.REQUEST_CODE
import com.irfanirawansukirman.abstraction.util.Const.Permission.CALL
import com.irfanirawansukirman.abstraction.util.Const.Permission.CAMERA
import com.irfanirawansukirman.abstraction.util.Const.Permission.FINE_LOCATION
import com.irfanirawansukirman.abstraction.util.Const.Permission.WRITE_STORAGE
import pl.aprilapps.easyphotopicker.ChooserType
import pl.aprilapps.easyphotopicker.EasyImage

fun AppCompatActivity.replaceFragment(
    @IdRes containerViewId: Int,
    fragment: Fragment,
    addToBackStack: Boolean
) {
    supportFragmentManager
        .beginTransaction()
        .replace(
            containerViewId,
            fragment,
            fragment.tag
        )
        .apply {
            if (addToBackStack) addToBackStack(fragment::class.java.simpleName)
        }
        .commit()
}

fun AppCompatActivity.addFragment(
    @IdRes containerViewId: Int,
    fragment: Fragment,
    addToBackStack: Boolean
) {
    supportFragmentManager
        .beginTransaction()
        .add(
            containerViewId,
            fragment,
            fragment.tag
        )
        .apply {
            if (addToBackStack) addToBackStack(fragment::class.java.simpleName)
        }
        .commit()
}

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

private lateinit var easyImage: EasyImage

fun createEasyImageBuilder(context: Context): EasyImage {
    if (!::easyImage.isInitialized) {
        easyImage = EasyImage.Builder(context)
            .setCopyImagesToPublicGalleryFolder(false)
            .setChooserType(ChooserType.CAMERA_AND_GALLERY)
            .setFolderName("EasyImage sample")
            .allowMultiple(true)
            .build()
    }
    return easyImage
}

fun getEasyImage() = easyImage

fun AppCompatActivity.openCamera() =
    easyImage.openCameraForImage(this)

fun AppCompatActivity.openGallery() =
    easyImage.openGallery(this)

fun AppCompatActivity.openCall(phoneNumber: String, isDirectCall: Boolean? = true) =
    intentCall(phoneNumber, isDirectCall, packageManager, this)

fun AppCompatActivity.openWifiSettings() =
    intentWirelessSettings(this)

fun AppCompatActivity.sendMessageToWhatsapp(targetPhoneNumber: String, message: String) =
    sendDirectMessage(this, targetPhoneNumber, message)

/*
 * List of self permissions
 */
fun AppCompatActivity.hasCameraPermission(): Boolean =
    ContextCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED

fun AppCompatActivity.hasWriteExtStoragePermission(): Boolean =
    ContextCompat.checkSelfPermission(this, WRITE_STORAGE) == PackageManager.PERMISSION_GRANTED

fun AppCompatActivity.hasLocationPermission(): Boolean =
    ContextCompat.checkSelfPermission(this, FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

fun AppCompatActivity.hasCallPermission(): Boolean =
    ContextCompat.checkSelfPermission(this, CALL) == PackageManager.PERMISSION_GRANTED
