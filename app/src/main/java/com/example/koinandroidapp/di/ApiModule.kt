package com.example.koinandroidapp.di

import com.example.koinandroidapp.data.network.factory.ApiClientFactory
import com.example.koinandroidapp.data.network.factory.IApiFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class ApiModule {

    @Binds
    abstract fun bindIApiFactory(impl: ApiClientFactory): IApiFactory
}