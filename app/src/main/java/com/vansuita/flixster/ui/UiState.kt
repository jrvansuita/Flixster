package com.vansuita.flixster.ui

import com.vansuita.flixster.data.model.Movie

data class UiState(
	val movies: List<Movie> = emptyList(),
	val isLoading: Boolean = true,
	val page: Int = 1,
	val error:String = ""
)