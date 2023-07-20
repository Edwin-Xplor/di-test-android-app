package com.example.koinandroidapp.usecases

import com.example.koinandroidapp.data.repository.IRepository
import javax.inject.Inject

class FetchRecommendedMoviesUseCase @Inject constructor(repository: IRepository) {

    val moviesList = repository.fetchRecommendedMovies()
}
