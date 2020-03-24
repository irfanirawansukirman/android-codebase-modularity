package com.irfanirawansukirman.abstraction.util.view;

import android.os.SystemClock
import android.view.View
import java.util.*

/**
 * A Throttled OnClickListener
 * Rejects clicks that are too close together in time.
 * This class is safe to use as an OnClickListener for multiple views, and will throttle each one separately.
 *
 *  * @param minimumIntervalMsec The minimum allowed time between clicks - any click sooner than this after a previous click will be rejected
 */
private const val minimumInterval = 1000L

class ThrottledOnClickListener(private val onClick: (view: View) -> Unit) : View.OnClickListener {
    private val lastClickMap: MutableMap<View, Long> = WeakHashMap()

    override fun onClick(clickedView: View) {
        val previousClickTimestamp = lastClickMap[clickedView]
        val currentTimestamp = SystemClock.uptimeMillis()

        lastClickMap[clickedView] = currentTimestamp
        if (previousClickTimestamp == null || currentTimestamp - previousClickTimestamp.toLong() > minimumInterval) {
            onClick.invoke((clickedView))
        }
    }
}