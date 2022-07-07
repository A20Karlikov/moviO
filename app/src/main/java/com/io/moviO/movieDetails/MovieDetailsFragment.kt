package com.io.moviO.movieDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.io.moviO.R
import com.io.moviO.databinding.FragmentMovieDetailsBinding
import com.io.moviO.domain.DataResult
import com.io.moviO.domain.Genre

private const val ARG_MOVIE_ID = "movie_id"

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private lateinit var binding: FragmentMovieDetailsBinding
    val viewModel: MovieDetailsViewModel by lazy { ViewModelProvider(this).get(MovieDetailsViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)
        requireArguments().getInt(ARG_MOVIE_ID).let { viewModel.getMovieById(it) }
        viewModel.movie.observe(viewLifecycleOwner, Observer {
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
                    this.context,
                    R.string.error_message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private fun convertGenres(list: List<Genre>?) =
        StringBuilder().apply {
            list?.map { genre ->
                this.append(genre.name)
                if (list.indexOf(genre) != list.lastIndex) {
                    this.append(", ")
                }
            }
        }.toString()

    companion object {
        fun newInstance(id: Int): MovieDetailsFragment = MovieDetailsFragment().also {
            it.arguments = Bundle().apply { putInt(ARG_MOVIE_ID, id) }
        }
    }
}