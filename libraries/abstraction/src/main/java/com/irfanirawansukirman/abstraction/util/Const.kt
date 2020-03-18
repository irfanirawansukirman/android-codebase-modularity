package com.irfanirawansukirman.abstraction.util

object Const {
    object Code {
        const val REQUEST_CODE = 1234
    }

    object Navigation {
        const val BASE_MOVIE = "myapp://movie/"
        const val TO_MOVIE = "$BASE_MOVIE{movieId}"
        const val PARAM_MOVIE_ID = "movieId"

        const val TO_MOVIE_DETAIL = "myapp://moviedetail/"
    }
}