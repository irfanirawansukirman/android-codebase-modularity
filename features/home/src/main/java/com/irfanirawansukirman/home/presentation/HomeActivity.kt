package com.irfanirawansukirman.home.presentation

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.airbnb.deeplinkdispatch.DeepLink
import com.irfanirawansukirman.abstraction.base.BaseActivity
import com.irfanirawansukirman.abstraction.util.Const.Navigation.TO_MOVIE
import com.irfanirawansukirman.abstraction.util.ext.showToast
import com.irfanirawansukirman.abstraction.util.ext.subscribe
import com.irfanirawansukirman.abstraction.util.state.ConnectionLost
import com.irfanirawansukirman.abstraction.util.state.Loading
import com.irfanirawansukirman.abstraction.util.state.Success
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.domain.model.response.LanguangeMapper
import com.irfanirawansukirman.home.R
import com.irfanirawansukirman.home.databinding.HomeActivityBinding
import org.koin.android.viewmodel.ext.android.viewModel

@DeepLink(TO_MOVIE)
class HomeActivity : BaseActivity<HomeActivityBinding>() {

    private val viewModel: HomeVM by viewModel()

    override fun getViewBinding(): HomeActivityBinding = HomeActivityBinding.inflate(layoutInflater)

    override fun loadObservers() {
        viewModel.uiState.subscribe(this, ::renderMoviesList)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            // val params = intent.extras ?: Bundle()

            getLanguage()
        }
    }

    override fun continuousCall() {
        // do nothing
    }

    override fun setupViewListener() {

    }

    override fun bindToolbar(): Toolbar? = mViewBinding.root.findViewById(R.id.toolbar)

    override fun enableBackButton(): Boolean = true

    private fun getLanguage() {
        viewModel.getLanguage()
    }

    private fun renderMoviesList(viewState: ViewState<LanguangeMapper>) {
        when (viewState) {
            is Loading -> {

            }
            is Success -> {
                val data = viewState.data.question
                showToast(this, data)
            }
            is Error -> {

            }
            is ConnectionLost -> {

            }
        }
    }

}
