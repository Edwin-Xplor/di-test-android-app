package com.example.koinandroidapp.application

import android.app.Application
import com.example.koinandroidapp.di.networkModule
import com.example.koinandroidapp.di.repositoryModule
import com.example.koinandroidapp.di.useCaseModule
import com.example.koinandroidapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    useCaseModule
                )
            )
        }
    }
}
