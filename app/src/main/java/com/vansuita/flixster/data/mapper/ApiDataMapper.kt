package com.vansuita.flixster.data.mapper

import com.vansuita.flixster.data.model.ApiCoverData
import com.vansuita.flixster.data.model.ApiDetailResponse
import com.vansuita.flixster.domain.model.Detail
import com.vansuita.flixster.domain.model.MovieCover
import com.vansuita.flixster.domain.model.TvShowCover

fun ApiCoverData.toMovie() = MovieCover(
	id = this.id,
	title = this.title,
	cover = this.cover
)

fun ApiCoverData.toTvShow() = TvShowCover(
	id = this.id,
	title = this.title,
	cover = this.cover
)

fun ApiDetailResponse.toDetail() = Detail(
	type = this.type ?: "Movies",
	genres = this.genres.map { it.name },
	authors = this.authors?.map { it.name } ?: emptyList(),
	overview = this.overview,
)
