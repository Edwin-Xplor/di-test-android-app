package com.example.koinandroidapp.di

import com.example.koinandroidapp.usecases.FetchRecommendedMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { FetchRecommendedMoviesUseCase(get()) }
}