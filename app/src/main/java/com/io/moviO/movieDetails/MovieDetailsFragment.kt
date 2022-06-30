package com.io.moviO.movieDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.io.moviO.R
import com.io.moviO.databinding.FragmentMovieDetailsBinding

private const val ARG_MOVIE = "movie"

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private lateinit var binding: FragmentMovieDetailsBinding
    val viewModel: MovieDetailsViewModel by lazy { ViewModelProvider(this).get(MovieDetailsViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)
        requireArguments().getString(ARG_MOVIE)?.let { viewModel.setMovie(it) }
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            binding.apply {
                movieName.text = it.name
                moviePoster.setBackgroundResource(it.poster)
                movieYear.text = it.year
                movieGenre.text = it.gerne
                movieCast.text = it.cast
                overviewText.text = it.overview
            }
        })
    }

    companion object {
        fun newInstance(id: String): MovieDetailsFragment = MovieDetailsFragment().also {
            it.arguments = Bundle().apply { putString(ARG_MOVIE, id) }
        }
    }
}