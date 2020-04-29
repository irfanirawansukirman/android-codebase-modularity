package com.irfanirawansukirman.abstraction.util.ext

import android.view.View
import android.view.ViewGroup
import com.irfanirawansukirman.abstraction.util.view.ThrottledOnClickListener

fun View.disable() {
    isEnabled = false
}

fun View.enable() {
    isEnabled = true
}

fun View.getEnableView() = isEnabled

fun View.visible() {
    if (this.visibility == View.GONE || this.visibility == View.INVISIBLE) this.visibility =
        View.VISIBLE
}

fun View.gone() {
    if (this.visibility == View.VISIBLE) this.visibility = View.GONE
}

fun View.invisible() {
    if (this.visibility == View.VISIBLE) this.visibility = View.INVISIBLE
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}

fun View.setOnSingleClickListener(action: (View) -> Unit) {
    setOnClickListener(ThrottledOnClickListener {
        action(it)
    })
}

