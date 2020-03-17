package com.irfanirawansukirman.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        Handler().postDelayed({
            setResult(4321)
            finish()
        }, 1000)
    }
}
