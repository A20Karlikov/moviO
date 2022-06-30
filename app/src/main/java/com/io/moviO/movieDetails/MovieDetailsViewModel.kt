package com.io.moviO.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.io.moviO.data.Movie
import com.io.moviO.domain.GetMovieByIdUseCase

class MovieDetailsViewModel : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    private val useCase = GetMovieByIdUseCase()

    fun setMovie(id: String) {
        _movie.value = useCase.getMovieById(id)
    }
}