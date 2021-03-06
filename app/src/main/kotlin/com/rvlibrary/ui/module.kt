package com.rvlibrary.ui

import com.rvlibrary.utils.ResourceProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainVM(get()) }
    single { ResourceProvider(androidApplication()) }
}