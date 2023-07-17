package com.example.koinandroidapp.di

import com.example.koinandroidapp.data.network.factory.ApiClientFactory
import com.example.koinandroidapp.data.network.factory.IApiFactory
import com.example.koinandroidapp.data.network.interceptor.NetworkConnectionInterceptor
import com.example.koinandroidapp.data.repository.IRepository
import com.example.koinandroidapp.data.repository.Repository
import com.example.koinandroidapp.usecases.FetchRecommendedMoviesUseCase
import com.example.koinandroidapp.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinNetworkModule = module {
    single { NetworkConnectionInterceptor(androidContext()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single<IApiFactory> { ApiClientFactory(get()) }
    single<IRepository> { Repository(get()) }
}

val koinViewModels = module {
    viewModel { MainViewModel(get()) }
}

val useCaseModule = module {
    factory { FetchRecommendedMoviesUseCase(get()) }
}