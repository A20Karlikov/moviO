package com.io.moviO.movieDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.io.moviO.R
import com.io.moviO.databinding.FragmentMovieDetailsBinding
import com.io.moviO.domain.DataResult
import com.io.moviO.domain.Movie

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()
    val viewModel: MovieDetailsViewModel by lazy { ViewModelProvider(this).get(MovieDetailsViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)
        viewModel.apply {
            getMovieById(args.movieId)
            movie.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is DataResult.Success ->
                        binding.apply {
                            movieName.text = it.value.title
                            movieReleaseDate.text = it.value.releaseDate
                            movieGenre.text = convertGenres(it.value.genres)
                            movieRating.text = "${it.value.voteAverage}/10"
                            overviewText.text = it.value.overview
                            Glide.with(this@MovieDetailsFragment)
                                .load(it.value.imageUrl)
                                .into(moviePoster)
                        }
                    is DataResult.Fail -> Toast.makeText(
                        binding.root.context,
                        R.string.error_message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    private fun convertGenres(list: List<Movie.Genre>?) =
        StringBuilder().apply {
            list?.map { genre ->
                this.append(genre.name)
                if (list.indexOf(genre) != list.lastIndex) {
                    this.append(", ")
                }
            }
        }.toString()
}