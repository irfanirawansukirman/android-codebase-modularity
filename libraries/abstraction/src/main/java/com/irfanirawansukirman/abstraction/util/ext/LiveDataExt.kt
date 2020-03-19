package com.irfanirawansukirman.abstraction.util.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.subscribe(owner: LifecycleOwner, crossinline onDataReceived: (T) -> Unit) =
    observe(owner, Observer { onDataReceived(it) })