package com.example.koinandroidapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("page")
    val pageIndex: Int,
    @SerializedName("results")
    val movieList: List<MovieResponse>
)
