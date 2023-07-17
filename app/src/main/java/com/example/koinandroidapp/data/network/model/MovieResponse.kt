package com.example.koinandroidapp.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Long,
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?
)