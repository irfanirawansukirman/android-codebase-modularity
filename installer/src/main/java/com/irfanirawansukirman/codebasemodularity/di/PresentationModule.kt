package com.irfanirawansukirman.codebasemodularity.di

import com.irfanirawansukirman.codebasemodularity.presentation.MainVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainVM(get()) }
}
