package com.io.moviO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.io.moviO.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val movieDetails = MovieDetailsFragment()
        val tr = supportFragmentManager.beginTransaction()
        tr.replace(R.id.fragment_container, movieDetails)
        tr.commit()
    }
}