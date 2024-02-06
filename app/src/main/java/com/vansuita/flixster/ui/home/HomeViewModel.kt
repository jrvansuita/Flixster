package com.vansuita.flixster.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vansuita.flixster.domain.usecase.GetMoviesCoversUseCase
import com.vansuita.flixster.domain.usecase.GetTvShowsCoversUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
	private val getMoviesCoversUseCase: GetMoviesCoversUseCase = GetMoviesCoversUseCase(),
	private val getTvShowsCoversUseCase: GetTvShowsCoversUseCase = GetTvShowsCoversUseCase(),
) : ViewModel() {

	private val _homeState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
	val homeState: StateFlow<HomeState> = _homeState

	init {
		load()
	}

	private fun load() {

		val exceptionHandler = CoroutineExceptionHandler { _, e ->
			_homeState.update {
				it.copy(
					error = e.message ?: e.toString(),
					isLoading = false
				)
			}
		}

		viewModelScope.launch(exceptionHandler) {
			_homeState.update {
				it.copy(isLoading = true)
			}

			val moviesDeferred = async(exceptionHandler) {
				getMoviesCoversUseCase()
			}

			val tvShowsDeferred = async(exceptionHandler) {
				getTvShowsCoversUseCase()
			}


			_homeState.update {
				it.copy(
					error = "",
					tvShowCovers = tvShowsDeferred.await(),
					movieCovers = moviesDeferred.await(),
					isLoading = false,
				)
			}
		}
	}

}