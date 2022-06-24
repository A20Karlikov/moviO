package com.io.moviO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.io.moviO.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieDetails = MovieDetailsFragment()
        val tr = supportFragmentManager.beginTransaction()
        tr.add(R.id.fragment_container, movieDetails)
        tr.commit()
    }
}