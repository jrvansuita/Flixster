package com.vansuita.flixster.data.datasource.remote.service

import com.vansuita.flixster.data.datasource.remote.api.Api
import com.vansuita.flixster.data.model.ApiCoverResponse
import com.vansuita.flixster.data.model.ApiDetailResponse

class ApiServiceImpl(private val api: Api = Api()) : ApiService {

	override suspend fun getTvShowsCovers(): ApiCoverResponse {
		return api.get("tv/popular")
	}

	override suspend fun getTvShowDetail(id: String): ApiDetailResponse {
		return api.get("tv/$id")
	}

	override suspend fun getMoviesCovers(): ApiCoverResponse {
		return api.get("movie/popular")
	}

	override suspend fun getMoviesDetail(id: String): ApiDetailResponse {
		return api.get("movie/$id")
	}
}