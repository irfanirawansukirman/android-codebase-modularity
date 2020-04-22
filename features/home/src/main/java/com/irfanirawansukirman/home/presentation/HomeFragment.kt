package com.irfanirawansukirman.home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.irfanirawansukirman.abstraction.base.BaseFragment
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

    override fun loadObservers() {
        viewModel.imagePath.subscribe(viewLifecycleOwner, ::showImageSelected)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {

    }

    override fun continuousCall() {

    }

    override fun setupViewListener() {
        mViewBinding.apply {
            btnCamera.setOnClickListener {
                getMyParentActivity().apply {
                    takePictureFromCamera()
                    // addFragment(this.mViewBinding.frameContainer.id, BlankFragment(), true)
                }
            }
            btnGallery.setOnClickListener {
                pickPictureFromGallery()
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

    private fun showImageSelected(imagePath: String) {
        mViewBinding.imgHome.loadImage(imagePath)
    }

}
