package com.io.moviO.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.moviO.data.DataResult
import com.io.moviO.data.modelMovie.Movie
import com.io.moviO.domain.GetMovieByIdUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {

    private val _movie = MutableLiveData<DataResult<Movie>>()
    val movie: LiveData<DataResult<Movie>> = _movie

    private val useCase = GetMovieByIdUseCase()

    fun getMovieById(id: Int) {
        viewModelScope.launch {
            val result = useCase.execute(id)
            _movie.value = result
        }
    }
}