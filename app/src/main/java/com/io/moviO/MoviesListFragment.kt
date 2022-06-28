package com.io.moviO

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.io.moviO.adapter.MovieListAdapter
import com.io.moviO.databinding.FragmentMoviesListBinding

class MoviesListFragment : Fragment(R.layout.fragment_movies_list), MovieListAdapter.SelectListener {
    private lateinit var binding : FragmentMoviesListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesListBinding.bind(view)

        modifyRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =  inflater.inflate(R.layout.fragment_movies_list, container, false)

    private fun modifyRecyclerView() {
        binding.moviesListRv.adapter = MovieListAdapter( this, createMovieList())
    }

    private fun createMovieList(): List<Movie> = listOf(
        Movie("Movie 1", R.drawable.ic_launcher_background, "2000", "Drama", "Cast Crew", "Overview"),
        Movie("Movie 2", R.drawable.ic_launcher_background, "2001", "Comedy", "Cast Crew", "Overview"),
        Movie("Movie 3", R.drawable.ic_launcher_background, "2002", "Drama", "Cast Crew", "Overview"),
        Movie("Movie 4", R.drawable.ic_launcher_background, "2003", "Comedy", "Cast Crew", "Overview"),
        Movie("Movie 5", R.drawable.ic_launcher_background, "2004", "Drama", "Cast Crew", "Overview"),
        Movie("Movie 6", R.drawable.ic_launcher_background, "2005", "Comedy", "Cast Crew", "Overview"),
        Movie("Movie 7", R.drawable.ic_launcher_background, "2006", "Drama", "Cast Crew", "Overview"),
        Movie("Movie 8", R.drawable.ic_launcher_background, "2007", "Comedy", "Cast Crew", "Overview"),
        Movie("Movie 9", R.drawable.ic_launcher_background, "2008", "Drama", "Cast Crew", "Overview"),
        Movie("Movie 10", R.drawable.ic_launcher_background, "2009", "Comedy", "Cast Crew", "Overview")
    )

    override fun onItemClicked(movie: Movie) {

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MovieDetailsFragment.newInstance(movie))
            .addToBackStack(this::class.java.name)
            .commit()
    }

}