package com.io.moviO.moviesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.io.moviO.common.Constants
import com.io.moviO.databinding.ListItemBinding
import com.io.moviO.domain.Movie

class MovieListAdapter(
    private val listener: OnMovieClickedListener,
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val newMovies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(newMovies[position], listener)

    override fun getItemCount() = newMovies.size

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, listener: OnMovieClickedListener) {
            binding.apply {
                movieNameMovieListTv.text = movie.title

                if (movie.imageUrl == Constants.IMAGE_URL_START_PART.plus(null)) {
                    movie.imageUrl = Constants.NO_IMAGE_URL
                }
                Glide.with(this.root)
                    .load(movie.imageUrl)
                    .into(moviePosterMovieListIv)

                root.setOnClickListener {
                    listener.onItemClicked(movie.id)
                }
            }
        }
    }

    fun updateMovieList(movies: List<Movie>) {
        newMovies.clear()
        newMovies.addAll(movies)
        notifyDataSetChanged()
    }

    interface OnMovieClickedListener {
        fun onItemClicked(id: Int)
    }
}