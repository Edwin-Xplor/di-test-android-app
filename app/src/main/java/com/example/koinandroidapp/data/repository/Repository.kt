package com.example.koinandroidapp.data.repository

import com.example.koinandroidapp.data.network.SafeApiRequestWrapper
import com.example.koinandroidapp.data.network.factory.IApiFactory
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiClientFactory: IApiFactory
) : IRepository, SafeApiRequestWrapper() {

    override fun fetchRecommendedMovies() = flow {
        emit(makeSafeRequest { apiClientFactory.client.fetchRecommendedMovies() })
    }
}