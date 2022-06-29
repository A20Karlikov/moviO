package com.io.moviO

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.io.moviO.databinding.FragmentMovieDetailsBinding

private const val ARG_MOVIE = "movie"

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private lateinit var binding: FragmentMovieDetailsBinding
    val viewModel: MovieDetailsViewModel by lazy { ViewModelProvider(this).get(MovieDetailsViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)
        requireArguments().getParcelable<Movie>(ARG_MOVIE)?.let { viewModel.createMovie(it) }
        viewModel.getMovie().observe(viewLifecycleOwner, Observer {
            binding.apply {
                movieName.text = it.name
                moviePoster.setBackgroundResource(it.poster)
                movieYear.text = it.year
                movieGenre.text = it.gerne
                movieCast.text = it.cast
                overviewText.text = it.overview
            }
        })


   //     requireArguments().getParcelable<Movie>(ARG_MOVIE)?.let { fillMovieDetails(it) }
    }

//    private fun fillMovieDetails(movie: Movie) {
//        binding.apply {
//            movieName.text = movie.name
//            moviePoster.setBackgroundResource(movie.poster)
//            movieYear.text = movie.year
//            movieGenre.text = movie.gerne
//            movieCast.text = movie.cast
//            overviewText.text = movie.overview
//        }
//    }

    companion object {
        fun newInstance(movie: Movie): MovieDetailsFragment = MovieDetailsFragment().also {
            it.arguments = Bundle().apply { putParcelable(ARG_MOVIE, movie) }
        }
    }
}