package com.io.moviO.domain

import com.io.moviO.data.Movie
import com.io.moviO.data.MoviesRepository

class GetMovieByIdUseCase {

    private val moviesRepo = MoviesRepository

    fun getMovieById(id: String): Movie {
        val movie = moviesRepo.getMovies().find { movie -> movie.id == id }
        return movie!!
    }
}