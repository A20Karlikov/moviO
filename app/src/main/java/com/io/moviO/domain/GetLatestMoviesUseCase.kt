package com.io.moviO.domain

import com.io.moviO.data.MoviesRepository
import java.util.*

class GetLatestMoviesUseCase(private val movieRepo: MoviesRepository = MoviesRepository) :
    UseCase<Unit, DataResult<List<Movie>>> {

    override suspend fun execute(param: Unit): DataResult<List<Movie>> {
        return try {
            DataResult.Success(
                movieRepo.latestMovies(
                    Unit, Calendar.getInstance().get(Calendar.YEAR)
                )
            )
        } catch (ex: Exception) {
            DataResult.Fail(ex)
        }
    }
}