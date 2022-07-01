package com.io.moviO.domain

import com.io.moviO.data.Movie
import com.io.moviO.data.MoviesRepository

class GetMoviesUseCase {
    private val moviesRepo = MoviesRepository

    suspend fun getMovies(): List<Movie> = moviesRepo.getMovies()
}