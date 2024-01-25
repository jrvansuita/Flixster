package com.vansuita.flixster.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.vansuita.flixster.R
import com.vansuita.flixster.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

	private val binding by lazy {
		ActivityMainBinding.inflate(layoutInflater)
	}

	private val adapter by lazy {
		MoviesAdapter()
	}

	private val viewModel: MainViewModel by viewModels()


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)

		binding.list.adapter = adapter

		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.uiState.collect { uiState ->
					binding.message.text =
						when {
							uiState.isLoading -> ""
							uiState.error.isNotEmpty() -> uiState.error
							uiState.movies.isEmpty() -> getString(R.string.no_movies_to_show)
							else -> ""
						}

					binding.progressBar.isVisible = uiState.isLoading
					binding.message.isVisible = binding.message.text.isNotEmpty()
					binding.list.isVisible = uiState.movies.isNotEmpty()

					if (binding.list.isVisible) adapter.addMore(uiState.movies)
				}
			}
		}
	}
}