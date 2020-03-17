package com.irfanirawansukirman.abstraction.util.state

sealed class ViewState<out T : Any>
class Success<out T : Any>(val data: T) : ViewState<T>()
class Error<out T : Any>(val error: Throwable) : ViewState<T>()
class Loading<out T : Any> : ViewState<T>()
class ConnectionLost<T : Any> : ViewState<T>()