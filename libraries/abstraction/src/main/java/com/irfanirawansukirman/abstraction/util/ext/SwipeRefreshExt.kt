package com.irfanirawansukirman.abstraction.util.ext

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun SwipeRefreshLayout.loading() {
    isRefreshing = true
}

fun SwipeRefreshLayout.finish() {
    isRefreshing = false
}