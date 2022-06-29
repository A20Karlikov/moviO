package com.io.moviO

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieDetailsViewModel : ViewModel() {

    private var _movie = MutableLiveData<Movie>()

    fun getMovie() : LiveData<Movie> = _movie

    fun createMovie(movie: Movie) {
        _movie.value = movie
    }
}