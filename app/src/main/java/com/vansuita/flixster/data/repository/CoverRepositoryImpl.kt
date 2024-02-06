package com.vansuita.flixster.data.repository

import com.vansuita.flixster.data.datasource.remote.service.ApiService
import com.vansuita.flixster.data.datasource.remote.service.ApiServiceImpl
import com.vansuita.flixster.data.mapper.toDetail
import com.vansuita.flixster.data.mapper.toMovie
import com.vansuita.flixster.data.mapper.toTvShow
import com.vansuita.flixster.domain.model.DataType

internal class CoverRepositoryImpl(private val apiService: ApiService = ApiServiceImpl()) :
	CoverRepository {
	override suspend fun getMoviesCovers() = apiService.getMoviesCovers().results.map {
		it.toMovie()
	}

	override suspend fun getTvShowsCovers() = apiService.getTvShowsCovers().results.map {
		it.toTvShow()
	}

	override suspend fun getDetail(id: String, type: DataType) = when (type) {
		DataType.Movie -> apiService.getMoviesDetail(id)
		DataType.TvShow -> apiService.getTvShowDetail(id)
	}.toDetail()

}