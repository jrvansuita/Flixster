package com.vansuita.flixster.data.repository

import com.vansuita.flixster.data.model.Movie
import com.vansuita.flixster.data.remote.MovieService

class MovieRepository(private val movieService: MovieService = MovieService()) {
	 suspend fun getPage(page: Int): List<Movie> {
		return movieService.getPage(page)
	}
}