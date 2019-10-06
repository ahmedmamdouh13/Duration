package com.ahmedmamdouh13.duration.application

import android.app.Application
import com.ahmedmamdouh13.duration.presentation.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class DurationApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            AndroidLogger()
            androidContext(this@DurationApplication)
            modules(module)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}