package com.example.koinandroidapp.data.network.factory

import com.example.koinandroidapp.data.network.client.ApiClient
import retrofit2.Retrofit

class ApiClientFactory(
    private val retrofit: Retrofit
) : IApiFactory {
    override val client: ApiClient
        get() = retrofit.create(ApiClient::class.java)
}

