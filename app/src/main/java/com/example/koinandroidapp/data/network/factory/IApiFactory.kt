package com.example.koinandroidapp.data.network.factory

import com.example.koinandroidapp.data.network.client.ApiClient


interface IApiFactory {
    val client: ApiClient
}