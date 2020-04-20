package com.irfanirawansukirman.abstraction.util.state

data class ViewState<T>(val status: Status, val data: T?, val error: Throwable?) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T): ViewState<T> {
            return ViewState(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable): ViewState<T> {
            return ViewState(Status.ERROR, null, error)
        }

        fun <T> loading(): ViewState<T> {
            return ViewState(Status.LOADING, null, null)
        }
    }
}