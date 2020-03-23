package com.irfanirawansukirman.abstraction.util.ext

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.irfanirawansukirman.abstraction.R

/**
 * Created by mochadwi on 12/26/2018.
 */

fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .apply(context.requestOptions(true))
        .into(this)
}

fun ImageView.loadImage(getApplicationContext: Context, file: Uri?) {
    Glide.with(getApplicationContext)
        .load(file)
        .apply(context.requestOptions(false))
        .into(this)
}

fun ImageView.loadImage(getApplicationContext: Context, file: Drawable?) {
    Glide.with(getApplicationContext)
        .load(file)
        .apply(context.requestOptions(false))
        .into(this)
}

fun ImageView.loadImage(getApplicationContext: Context, file: Bitmap?) {
    Glide.with(getApplicationContext)
        .asBitmap()
        .load(file)
        .apply(context.requestOptions(false))
        .into(this)
}

private fun Context.requestOptions(isOriginal: Boolean): RequestOptions =
    RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply {
            if (isOriginal) override(getWindowWidth(), Target.SIZE_ORIGINAL)
            else fitCenter().centerCrop()
        }
        .error(R.mipmap.ic_launcher)
        .encodeFormat(Bitmap.CompressFormat.WEBP) // compress image into webp
        .format(DecodeFormat.PREFER_RGB_565) // support decoding png or gif (image w/ alpha)
        .placeholder(myCircularProgressDrawable())
        .diskCacheStrategy(DiskCacheStrategy.ALL) // cache remote and local data directly
        .skipMemoryCache(false)

fun Context.myCircularProgressDrawable(): CircularProgressDrawable =
    CircularProgressDrawable(this).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }

fun Context.getWindowWidth(): Int {
    val display = (this as Activity).windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)

    return size.x
}