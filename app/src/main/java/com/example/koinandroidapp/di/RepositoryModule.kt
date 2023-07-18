package com.example.koinandroidapp.di

import com.example.koinandroidapp.data.network.factory.ApiClientFactory
import com.example.koinandroidapp.data.network.factory.IApiFactory
import com.example.koinandroidapp.data.network.interceptor.NetworkConnectionInterceptor
import com.example.koinandroidapp.data.repository.IRepository
import com.example.koinandroidapp.data.repository.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single { NetworkConnectionInterceptor(androidContext()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single<IApiFactory> { ApiClientFactory(get()) }
}

val repositoryModule = module {
    factory<IRepository> { Repository(get()) }
}