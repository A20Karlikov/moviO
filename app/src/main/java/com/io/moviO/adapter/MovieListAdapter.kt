package com.io.moviO.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.io.moviO.Movie
import com.io.moviO.databinding.ListItemBinding

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
            binding.movieNameMovieListTv.text = movie.name
            binding.moviePosterMovieListIv.setImageResource(movie.poster)
            binding.root.setOnClickListener {
                listener.onItemClicked(movie)
            }
        }
    }

    fun updateMovieList(movies: List<Movie>) {
        newMovies.clear()
        newMovies.addAll(movies)
        notifyDataSetChanged()
    }

    interface OnMovieClickedListener {
        fun onItemClicked(movie: Movie)
    }
}