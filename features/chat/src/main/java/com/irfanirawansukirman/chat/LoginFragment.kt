package com.irfanirawansukirman.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.irfanirawansukirman.abstraction.base.BaseFragment
import com.irfanirawansukirman.chat.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginActivity>() {

    override fun getViewBinding(): FragmentLoginBinding =
        FragmentLoginBinding.inflate(layoutInflater)

    override fun loadObservers() {

    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
         mViewBinding.text.text = "Hallo Irfan Irawan Sukirman"
    }

    override fun continuousCall() {

    }

    override fun setupViewListener() {

    }

}
