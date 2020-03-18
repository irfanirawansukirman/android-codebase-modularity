package com.irfanirawansukirman.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLink
import com.irfanirawansukirman.abstraction.util.Const.Navigation.PARAM_MOVIE_ID
import com.irfanirawansukirman.abstraction.util.Const.Navigation.TO_MOVIE
import com.irfanirawansukirman.abstraction.util.ext.showToast

@DeepLink(TO_MOVIE)
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            val params = intent.extras ?: Bundle()
            showToast(this, params.getString(PARAM_MOVIE_ID))
        }
    }
}
