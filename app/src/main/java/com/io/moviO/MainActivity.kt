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
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container, MoviesListFragment())
        .commit()
    }
}