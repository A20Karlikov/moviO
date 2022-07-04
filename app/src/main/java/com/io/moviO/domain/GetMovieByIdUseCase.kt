package com.io.moviO.domain

import com.io.moviO.data.DataResult
import com.io.moviO.data.Movie
import com.io.moviO.data.MoviesRepository

class GetMovieByIdUseCase : UseCase<String, DataResult<Movie>> {
    private val moviesRepo = MoviesRepository

    override suspend fun execute(id: String): DataResult<Movie> {
        return try {
            val movie = moviesRepo.getMovies().first { movie -> movie.id == id }
            DataResult.Success(movie)
        } catch (ex: Exception) {
            DataResult.Fail(ex)
        }

    }
}