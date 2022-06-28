package com.io.moviO.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.io.moviO.Movie
import com.io.moviO.databinding.ListItemBinding

class MovieListAdapter(
    private val listener: SelectListener,
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(movies[position], listener)

    override fun getItemCount() = movies.size

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, listener: SelectListener) {
            binding.movieNameMovieListTv.text = movie.name
            binding.moviePosterMovieListIv.setImageResource(movie.poster)
            binding.root.setOnClickListener {
                listener.onItemClicked(movie)
            }
        }
    }

    interface SelectListener {
        fun onItemClicked(movie: Movie)
    }
}