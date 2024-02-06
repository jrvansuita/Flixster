package com.vansuita.flixster.data.datasource.remote.service

import com.vansuita.flixster.data.model.ApiCoverResponse
import com.vansuita.flixster.data.model.ApiDetailResponse

interface ApiService {
	suspend fun getTvShowsCovers(): ApiCoverResponse
	suspend fun getTvShowDetail(id: String): ApiDetailResponse

	suspend fun getMoviesCovers(): ApiCoverResponse
	suspend fun getMoviesDetail(id: String): ApiDetailResponse
}