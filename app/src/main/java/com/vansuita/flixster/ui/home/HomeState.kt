package com.vansuita.flixster.ui.home

import com.vansuita.flixster.domain.model.MovieCover
import com.vansuita.flixster.domain.model.TvShowCover

data class HomeState(
	val movieCovers: List<MovieCover> = emptyList(),
	val tvShowCovers: List<TvShowCover> = emptyList(),
	val isLoading: Boolean = true,
	val error: String = ""
)