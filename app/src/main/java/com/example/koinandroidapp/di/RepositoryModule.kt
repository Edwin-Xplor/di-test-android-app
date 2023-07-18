package com.example.koinandroidapp.di

import com.example.koinandroidapp.data.repository.IRepository
import com.example.koinandroidapp.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: Repository): IRepository
}