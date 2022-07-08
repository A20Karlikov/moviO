package com.io.moviO.domain

import com.io.moviO.data.MoviesRepository

class GetLatestMoviesUseCase(private val movieRepo: MoviesRepository = MoviesRepository) :
    UseCase<Unit, DataResult<List<Movie>>> {

    override suspend fun execute(param: Unit): DataResult<List<Movie>> {
        return try {
            DataResult.Success(movieRepo.latestMovies())
        } catch (ex: Exception) {
            DataResult.Fail(ex)
        }
    }
}