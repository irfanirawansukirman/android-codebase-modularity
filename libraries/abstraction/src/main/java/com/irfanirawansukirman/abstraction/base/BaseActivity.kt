package com.irfanirawansukirman.abstraction.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.viewbinding.ViewBinding
import com.irfanirawansukirman.abstraction.R
import com.irfanirawansukirman.abstraction.util.ext.makeStatusBarTransparent
import com.irfanirawansukirman.abstraction.util.ext.overridePendingTransitionExit

abstract class BaseActivity<VB : ViewBinding>(private val viewBinder: (LayoutInflater) -> ViewBinding) :
    AppCompatActivity() {

    val mViewBinding by lazy { viewBinder.invoke(layoutInflater) as VB }

    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        makeStatusBarTransparent()
        setupToolbar()
        onFirstLaunch(savedInstanceState)
        setupViewListener()
        loadObservers()
    }

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    /**
     * Function for load livedata observer from viewmodel
     */
    abstract fun loadObservers()

    /**
     * Function for first launching like as onCreate
     */
    abstract fun onFirstLaunch(savedInstanceState: Bundle?)

    /**
     * Function for continously call after onCreate and onResume
     */
    abstract fun continuousCall()

    /**
     * Function for setup view listener
     */
    abstract fun setupViewListener()

    /**
     * Function for get toolbarId
     * @param void
     * @return Toolbar
     */
    abstract fun bindToolbar(): Toolbar?

    /**
     * Function for enable back button toolbar.
     * @return Boolean
     */
    abstract fun enableBackButton(): Boolean

    override fun onStart() {
        super.onStart()
        continuousCall()
    }

    /**
     * Method listener for menu toolbar
     * @return Boolean
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }

    /**
     * Function for setup toolbar [inactive|active]
     * @param void
     * @return void
     */
    private fun setupToolbar() {
        bindToolbar()?.let {
            setupToolbarTopInset()
            mToolbar = it
            setSupportActionBar(mToolbar)
            supportActionBar?.apply {
                setDisplayShowTitleEnabled(false)
                setDisplayHomeAsUpEnabled(enableBackButton())
            }
        }
    }

    private fun setupToolbarTopInset() {
        ViewCompat.setOnApplyWindowInsetsListener(mViewBinding.root) { _, insets ->
            val viewInsetToolbar = mViewBinding.root.findViewById<View>(R.id.viewInsetToolbar)
            viewInsetToolbar.layoutParams.height = insets.systemWindowInsetTop
            insets.consumeSystemWindowInsets()
        }
    }

    fun getParentToolbar(): Toolbar? = mToolbar

}