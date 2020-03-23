package com.irfanirawansukirman.abstraction.util

object Const {
    object Code {
        const val REQUEST_CODE = 1234
    }

    object View {
        const val MESSAGE_TOAST_TYPE = 0
        const val MESSAGE_SNACK_TYPE = 1

        const val EMPTY_TOOLBAR = 0
    }

    object Navigation {
        const val BASE = "myapp://"
        const val TO_MOVIE = "${BASE}movie"
        const val MOVIE_TITLE = "MOVIE_TITLE"

        const val TO_CHAT = "${BASE}chat"
    }

    object DateTime {
        const val DATE_TIME_DEFAULT = "yyyy-MM-dd HH:mm:ss" // HH (24) hh (12)
    }

    object KeyParam {
        const val TEST = "TEST"
    }
}