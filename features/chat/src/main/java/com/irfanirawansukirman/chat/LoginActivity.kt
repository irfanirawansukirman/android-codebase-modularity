package com.irfanirawansukirman.chat

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.airbnb.deeplinkdispatch.DeepLink
import com.irfanirawansukirman.abstraction.base.BaseActivity
import com.irfanirawansukirman.abstraction.util.Const.Navigation.MOVIE_TITLE
import com.irfanirawansukirman.abstraction.util.Const.Navigation.TO_CHAT
import com.irfanirawansukirman.abstraction.util.ext.showToast
import com.irfanirawansukirman.chat.databinding.LoginActivityBinding

@DeepLink(TO_CHAT)
class LoginActivity : BaseActivity<LoginActivityBinding>() {

    override fun getViewBinding(): LoginActivityBinding =
        LoginActivityBinding.inflate(layoutInflater)

    override fun loadObservers() {

    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            val params = intent.extras ?: Bundle()
            showToast(this, params.getString(MOVIE_TITLE))
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, LoginFragment())
            .commit()
    }

    override fun continuousCall() {
        // do nothing
    }

    override fun setupViewListener() {

    }

    override fun bindToolbar(): Toolbar? = mViewBinding.root.findViewById(R.id.toolbar)

    override fun enableBackButton(): Boolean = true

}

