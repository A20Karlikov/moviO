package com.io.moviO

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.io.moviO.databinding.FragmentMovieDetailsBinding

private const val ARG_MOVIE = "movie"

class MovieDetailsFragment() : Fragment(R.layout.fragment_movie_details){

    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)

        val movie = requireArguments().getParcelable<Movie>(ARG_MOVIE)
        if (movie != null)  fillMovieDetails(movie)
    }

    private fun fillMovieDetails(movie: Movie){
        binding.apply {
            movieName.text = movie.name
            moviePoster.setBackgroundResource(movie.poster)
            movieYear.text = movie.year
            movieGenre.text = movie.gerne
            movieCast.text = movie.cast
            overviewText.text = movie.overview
        }
    }

    companion object {
        fun newInstance(movie: Movie): MovieDetailsFragment {
            val createdMovieDetailsFragment = MovieDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_MOVIE, movie)
            createdMovieDetailsFragment.arguments = bundle
            return createdMovieDetailsFragment
        }
    }
}