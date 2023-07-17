package com.example.koinandroidapp.di

import com.example.koinandroidapp.BuildConfig
import com.example.koinandroidapp.data.network.interceptor.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("${BuildConfig.API_URL}${BuildConfig.THEMOVIEDB_ACCOUNT_ID}/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(3, TimeUnit.MINUTES)
        .addInterceptor(provideHTTPLoginInterceptor)
        .addInterceptor(networkConnectionInterceptor)
        .build()
}

private val provideHTTPLoginInterceptor: HttpLoggingInterceptor by lazy {
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

