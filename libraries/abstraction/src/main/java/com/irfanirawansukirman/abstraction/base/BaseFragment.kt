package com.irfanirawansukirman.abstraction.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, PA : AppCompatActivity>(private val viewBinder: (LayoutInflater) -> ViewBinding) :
    Fragment() {

    protected lateinit var mParentActivity: PA

    val mViewBinding by lazy { viewBinder.invoke(LayoutInflater.from(requireContext())) as VB }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = mViewBinding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewListener()
        onFirstLaunch(savedInstanceState)
        loadObservers()
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

    abstract fun onDestroyActivities()

    override fun onStart() {
        super.onStart()
        continuousCall()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroyActivities()
    }

    fun getMyParentActivity(): PA = requireActivity() as PA

    fun getMyParentFragment(): PA = parentFragment as PA

}