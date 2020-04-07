package com.irfanirawansukirman.home.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.airbnb.deeplinkdispatch.DeepLink
import com.irfanirawansukirman.abstraction.base.BaseActivity
import com.irfanirawansukirman.abstraction.util.Const.Navigation.TO_MOVIE
import com.irfanirawansukirman.abstraction.util.Const.Permission.CAMERA_NAME
import com.irfanirawansukirman.abstraction.util.ext.*
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.abstraction.util.state.ViewState.Status.*
import com.irfanirawansukirman.data.common.util.Connectivity
import com.irfanirawansukirman.domain.model.response.LanguangeMapper
import com.irfanirawansukirman.home.R
import com.irfanirawansukirman.home.databinding.HomeActivityBinding
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource

@DeepLink(TO_MOVIE)
class HomeActivity : BaseActivity<HomeActivityBinding>(HomeActivityBinding::inflate),
    PermissionListener {

    private val viewModel: HomeVM by viewModel()

    private val connectivity: Connectivity by inject()

    override fun loadObservers() {
        viewModel.languageState.subscribe(this, ::renderMoviesList)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            // val params = intent.extras ?: Bundle()

            if (connectivity.isNetworkAvailable()) getLanguage() else showToast(
                this,
                "Connection is lost"
            )

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, HomeFragment())
                .commit()

            createEasyImageBuilder(this)
        }
    }

    override fun continuousCall() {

    }

    override fun setupViewListener() {

    }

    override fun bindToolbar(): Toolbar? = mViewBinding.root.findViewById(R.id.toolbar)

    override fun enableBackButton(): Boolean = true

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        response?.let {
            if (it.permissionName == CAMERA_NAME) { // open camera
                openCamera()
            } else { // open gallery
                openGallery()
            }
        }
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
        showRationaleCameraDialog(token)
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        response?.let {
            if (it.isPermanentlyDenied) {
                showToast(
                    this,
                    "Masuk ke setting aplikasi untuk mengizinkan penggunaan kamera secara manual"
                )
            } else {
                showToast(this, "Fitur tidak akan berjalan semestinya jika kamera tidak diizinkan")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getEasyImage().handleActivityResult(
            requestCode,
            resultCode,
            data,
            this,
            object : DefaultCallback() {
                override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                    showImageSelected(imageFiles[0].file.absolutePath)
                }
            })
    }

    private fun getLanguage() {
        viewModel.getLanguage()
    }

    private fun renderMoviesList(viewState: ViewState<LanguangeMapper>) {
        when (viewState.status) {
            LOADING -> { }
            SUCCESS -> {
                val data = viewState.data?.question
                data?.let {
                    showToast(this@HomeActivity, it)
                }
            }
            ERROR -> { }
        }
    }

    private fun showRationaleCameraDialog(token: PermissionToken?) {
        showAlertDialog(
            this,
            "Permission",
            "Anda harus mengizinkan penggunaan kamera untuk fitur ini",
            "Batal",
            "Izinkan",
            token
        )
    }

    private fun showImageSelected(imagePath: String) {
        viewModel.setupImagePath(imagePath)
    }

}
