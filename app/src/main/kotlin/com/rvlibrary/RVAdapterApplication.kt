package com.rvlibrary

import android.app.Application
import com.rvlibrary.ui.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class
 */
class RVAdapterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(mainModule))
        }
    }
}