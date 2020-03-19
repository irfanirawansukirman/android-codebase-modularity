package com.irfanirawansukirman.data.di

import com.irfanirawansukirman.data.common.util.Connectivity
import com.irfanirawansukirman.data.common.util.ConnectivityImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val connectivityModule = module {
    factory<Connectivity> { ConnectivityImpl(androidContext()) }
}