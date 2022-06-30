package com.io.moviO.moviesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.io.moviO.data.Movie
import com.io.moviO.domain.GetMoviesUseCase

class MoviesListViewModel : ViewModel() {

    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val useCase = GetMoviesUseCase()

    init {
        _movies.value = useCase.getMovies()
    }
}