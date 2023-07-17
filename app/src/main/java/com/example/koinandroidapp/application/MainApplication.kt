package com.example.koinandroidapp.application

import android.app.Application
import com.example.koinandroidapp.di.koinNetworkModule
import com.example.koinandroidapp.di.koinViewModels
import com.example.koinandroidapp.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    koinNetworkModule,
                    koinViewModels,
                    useCaseModule
                )
            )
        }
    }
}
