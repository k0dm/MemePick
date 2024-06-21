package com.bugbender.memepick.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bugbender.memepick.R
import com.bugbender.memepick.authentication.AuthenticationScreen
import com.bugbender.memepick.databinding.ActivityMainBinding
import com.bugbender.memepick.memes.presentation.MemesScreen
import com.bugbender.profile.ProfileScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.memes -> {
                    viewModel.navigateTo(screen = MemesScreen)
                    true
                }

                R.id.profile -> {
                    viewModel.navigateTo(screen = ProfileScreen)
                    true
                }

                else -> {
                    false
                }
            }
        }

        viewModel.liveData().observe(this) { screen ->
            screen.show(binding.container.id, supportFragmentManager)
        }

        viewModel.init(isFirstRun = savedInstanceState == null)
    }
}