package com.irfanirawansukirman.home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.irfanirawansukirman.abstraction.base.BaseFragment
import com.irfanirawansukirman.abstraction.util.Const.Permission.CALL
import com.irfanirawansukirman.abstraction.util.Const.Permission.CAMERA
import com.irfanirawansukirman.abstraction.util.Const.Permission.WRITE_STORAGE
import com.irfanirawansukirman.abstraction.util.ext.*
import com.irfanirawansukirman.home.databinding.HomeFragmentBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeActivity>(HomeFragmentBinding::inflate) {

    private val viewModel: HomeVM by sharedViewModel()

    private var phoneNumber = ""

    override fun loadObservers() {
        viewModel.apply {
            imagePath.subscribe(viewLifecycleOwner, ::showImageSelected)
            callState.subscribe(viewLifecycleOwner, ::navigateCall)
        }
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {

    }

    override fun continuousCall() {

    }

    override fun setupViewListener() {
        mViewBinding.apply {
            btnCamera.setOnSingleClickListener {
                getMyParentActivity().apply {
                    takePictureFromCamera()
                    // addFragment(this.mViewBinding.frameContainer.id, BlankFragment(), true)
                }
            }
            btnGallery.setOnSingleClickListener {
                pickPictureFromGallery()
            }
            btnCall.setOnSingleClickListener {
                phoneNumber = "+6289628157908"
                directCall(phoneNumber)
            }
        }
    }

    override fun onDestroyActivities() {

    }

    private fun takePictureFromCamera() {
        getMyParentActivity().apply {
            if (!hasCameraPermission()) { // request permission for using camera
                requestSinglePermission(CAMERA, this, this)
            } else { // camera permission is granted
                openCamera()
            }
        }
    }

    private fun pickPictureFromGallery() {
        getMyParentActivity().apply {
            if (!hasWriteExtStoragePermission()) {
                requestSinglePermission(WRITE_STORAGE, this, this)
            } else {
                openGallery()
            }
        }
    }

    private fun directCall(phoneNumber: String) {
        getMyParentActivity().apply {
            if (!hasCallPermission()) {
                requestSinglePermission(CALL, this, this)
            } else {
                openCall(phoneNumber)
            }
        }
    }


    private fun showImageSelected(imagePath: String) {
        mViewBinding.imgHome.loadImage(imagePath)
    }

    private fun navigateCall(state: Boolean) {
        if (state) getMyParentActivity().openCall(phoneNumber)
    }

}
