package com.irfanirawansukirman.codebasemodularity.di

import com.irfanirawansukirman.chat.LoginVM
import com.irfanirawansukirman.codebasemodularity.presentation.MainVM
import com.irfanirawansukirman.home.presentation.HomeVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainVM(get(), get()) }
    viewModel { HomeVM(get(), get()) }
    viewModel { LoginVM() }
}
