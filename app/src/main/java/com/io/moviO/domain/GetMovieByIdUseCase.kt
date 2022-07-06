package com.io.moviO.domain

import com.io.moviO.data.DataResult
import com.io.moviO.data.MoviesRepository
import com.io.moviO.data.modelMovie.Movie

class GetMovieByIdUseCase(private val moviesRepo: MoviesRepository = MoviesRepository) :
    UseCase<Int, DataResult<Movie>> {


    override suspend fun execute(id: Int): DataResult<Movie> {
        return try {
            DataResult.Success(moviesRepo.getMovieById(id))
        } catch (ex: Exception) {
            DataResult.Fail(ex)
        }

    }
}