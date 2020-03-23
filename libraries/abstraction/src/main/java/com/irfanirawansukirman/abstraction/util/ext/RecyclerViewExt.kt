package com.irfanirawansukirman.abstraction.util.ext

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

fun RecyclerView.linearList(context: Context) {
    layoutManager = LinearLayoutManager(context)
    setHasFixedSize(true)
    setItemViewCacheSize(30)
}

fun RecyclerView.horizontalList(context: Context) {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    setHasFixedSize(true)
    setItemViewCacheSize(30)
}

fun RecyclerView.gridList(context: Context, column: Int) {
    layoutManager = GridLayoutManager(context, column)
    setHasFixedSize(true)
    setItemViewCacheSize(30)
}

fun RecyclerView.staggeredList(column: Int, orientation: Int) {
    layoutManager = StaggeredGridLayoutManager(column, orientation)
    setHasFixedSize(true)
    setItemViewCacheSize(30)
}