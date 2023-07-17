package com.example.koinandroidapp.data.repository

import com.example.koinandroidapp.data.network.model.ApiResponse
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun fetchRecommendedMovies(): Flow<ApiResponse?>
}