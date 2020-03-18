package com.irfanirawansukirman.codebasemodularity.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.irfanirawansukirman.chat.deeplink.ChatDeepLinkModule
import com.irfanirawansukirman.chat.deeplink.ChatDeepLinkModuleLoader
import com.irfanirawansukirman.home.deeplink.HomeDeepLinkModule
import com.irfanirawansukirman.home.deeplink.HomeDeepLinkModuleLoader

@DeepLinkHandler(
    AppDeepLinkModule::class,
    ChatDeepLinkModule::class,
    HomeDeepLinkModule::class
)
class DeepLinkRouterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deepLinkDelegate =
            DeepLinkDelegate(
                AppDeepLinkModuleLoader(),
                ChatDeepLinkModuleLoader(),
                HomeDeepLinkModuleLoader()
            )
        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}