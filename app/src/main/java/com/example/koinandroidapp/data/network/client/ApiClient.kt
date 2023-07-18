package com.example.koinandroidapp.data.network.client

import com.example.koinandroidapp.data.network.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("movie/recommendations")
    suspend fun fetchRecommendedMovies(@Query("page") page: Int = 1): Response<ApiResponse>
}