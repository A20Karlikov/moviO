package com.io.moviO

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.io.moviO.databinding.ActivityMainBinding
import com.io.moviO.moviesList.MoviesListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container, MoviesListFragment())
        .commit()
    }
}