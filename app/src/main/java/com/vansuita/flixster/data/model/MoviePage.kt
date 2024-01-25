package com.vansuita.flixster.data.model


data class MoviePage(
	val page: Int,
	val results: List<Movie>,
)