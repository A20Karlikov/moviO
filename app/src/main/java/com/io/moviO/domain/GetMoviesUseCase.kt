package com.io.moviO.domain

import com.io.moviO.data.DataResult
import com.io.moviO.data.Movie
import com.io.moviO.data.MoviesRepository

class GetMoviesUseCase : UseCase<MoviesRepository, DataResult<List<Movie>>> {
    private val moviesRepo = MoviesRepository

    override suspend fun execute(param: MoviesRepository): DataResult<List<Movie>> {
        return try {
            val movies = moviesRepo.getMovies()
            DataResult.Success(movies)
        } catch (ex: Exception) {
            DataResult.Fail(ex)
        }
    }
}