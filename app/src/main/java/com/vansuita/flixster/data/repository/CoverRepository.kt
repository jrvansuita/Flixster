package com.vansuita.flixster.data.repository

import com.vansuita.flixster.domain.model.DataType
import com.vansuita.flixster.domain.model.Detail
import com.vansuita.flixster.domain.model.MovieCover
import com.vansuita.flixster.domain.model.TvShowCover

interface CoverRepository {
	suspend fun getMoviesCovers(): List<MovieCover>

	suspend fun getTvShowsCovers(): List<TvShowCover>

	suspend fun getDetail(id: String, type: DataType): Detail

}