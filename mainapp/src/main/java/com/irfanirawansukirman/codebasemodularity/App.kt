package com.irfanirawansukirman.codebasemodularity

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.facebook.FacebookSdk
import com.irfanirawansukirman.codebasemodularity.di.appModule
import com.irfanirawansukirman.codebasemodularity.di.presentationModule
import com.irfanirawansukirman.data.di.connectivityModule
import com.irfanirawansukirman.data.di.networkModule
import com.irfanirawansukirman.data.di.repositoryModule
import com.irfanirawansukirman.domain.di.interactionModule
import com.onesignal.OneSignal
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        // OneSignal Initialization
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()

        startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG) androidLogger(Level.DEBUG)
            modules(appModules + domainModules + dataModules)
        }

        FacebookSdk.sdkInitialize(applicationContext)
    }

    companion object {
        lateinit var instance: Application
            private set
    }
}

val appModules = listOf(presentationModule, appModule)
val domainModules = listOf(interactionModule)
val dataModules = listOf(networkModule, repositoryModule, connectivityModule)
