package com.io.moviO.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.moviO.data.Movie
import com.io.moviO.domain.GetMovieByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    private val useCase = GetMovieByIdUseCase()

    fun getMovieById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = useCase.getMovieById(id)
            withContext(Dispatchers.Main) {
                _movie.value = result
            }
        }
    }
}