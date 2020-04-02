package com.irfanirawansukirman.chat

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.irfanirawansukirman.abstraction.base.BaseFragment
import com.irfanirawansukirman.abstraction.util.ext.showToast
import com.irfanirawansukirman.chat.databinding.FragmentLoginBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginActivity>(FragmentLoginBinding::inflate) {

    val vm: LoginVM by sharedViewModel()

    override fun loadObservers() {
        vm.outputNumber.observe(viewLifecycleOwner, Observer { angka ->
            showToast(requireContext(), "From Activity $angka")
        })
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        mViewBinding.text.apply {
            text = "Hallo Irfan Irawan Sukirman"
            setOnClickListener {
                vm.inputNumber.value = 15
            }
        }
    }

    override fun continuousCall() {

    }

    override fun setupViewListener() {

    }

}
