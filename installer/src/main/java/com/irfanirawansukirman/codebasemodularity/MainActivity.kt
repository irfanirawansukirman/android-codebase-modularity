package com.irfanirawansukirman.codebasemodularity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.irfanirawansukirman.abstraction.util.Const.Navigation.BASE_MOVIE
import com.irfanirawansukirman.abstraction.util.ext.navigate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            navigate(BASE_MOVIE + "irfanirawansukirman")
        }, 1000)
    }

}
