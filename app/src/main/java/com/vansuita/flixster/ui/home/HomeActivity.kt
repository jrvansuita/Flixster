package com.vansuita.flixster.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.vansuita.flixster.R
import com.vansuita.flixster.databinding.ActivityHomeBinding
import com.vansuita.flixster.databinding.CoverItemBinding
import com.vansuita.flixster.domain.model.Cover
import com.vansuita.flixster.ui.detail.DetailActivity
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

	private val binding by lazy {
		ActivityHomeBinding.inflate(layoutInflater)
	}

	private val moviesCoversAdapter by lazy {
		CoversListAdapter(::onCoverClick)
	}

	private val tvShowsCoversAdapter by lazy {
		CoversListAdapter(::onCoverClick)
	}

	private val viewModel: HomeViewModel by viewModels()


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)

		binding.moviesRecyclerView.adapter = moviesCoversAdapter
		binding.tvShowsRecyclerView.adapter = tvShowsCoversAdapter

		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.homeState.collect { uiState ->
					binding.message.text =
						when {
							uiState.isLoading -> ""
							uiState.error.isNotEmpty() -> uiState.error
							uiState.movieCovers.isEmpty() -> getString(R.string.no_movies_to_show)
							else -> ""
						}

					binding.progressBar.isVisible = uiState.isLoading
					binding.message.isVisible = binding.message.text.isNotEmpty()

					binding.moviesRecyclerView.isVisible = uiState.movieCovers.isNotEmpty()
					binding.tvShowsRecyclerView.isVisible = uiState.tvShowCovers.isNotEmpty()

					binding.topMovies.isVisible = binding.moviesRecyclerView.isVisible
					binding.topTvShows.isVisible = binding.tvShowsRecyclerView.isVisible

					if (binding.moviesRecyclerView.isVisible) moviesCoversAdapter.addMore(uiState.movieCovers)
					if (binding.moviesRecyclerView.isVisible) tvShowsCoversAdapter.addMore(uiState.tvShowCovers)
				}
			}
		}
	}


	private fun onCoverClick(binding: CoverItemBinding, cover: Cover) {
		DetailActivity.launch(this, binding.cover, binding.title, cover)
	}

}