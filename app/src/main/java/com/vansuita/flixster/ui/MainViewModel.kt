package com.vansuita.flixster.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vansuita.flixster.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
	private val movieRepository: MovieRepository = MovieRepository()
) :
	ViewModel() {
	private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
	val uiState: StateFlow<UiState> = _uiState

	init {
		load()
	}

	private fun load() {
		viewModelScope.launch {
			_uiState.update {
				it.copy(isLoading = true)
			}

			try {
				_uiState.update {
					it.copy(
						error = "",
						movies = movieRepository.getPage(uiState.value.page),
						isLoading = false,
						page = it.page + 1
					)
				}
			} catch (e: Exception) {
				_uiState.update {
					it.copy(
						error = e.message ?: e.toString(),
						isLoading = false
					)
				}
			}
		}
	}

}