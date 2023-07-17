package com.example.koinandroidapp.usecases

import com.example.koinandroidapp.data.repository.IRepository

class FetchRecommendedMoviesUseCase(repository: IRepository) {

    val moviesList = repository.fetchRecommendedMovies()
}
