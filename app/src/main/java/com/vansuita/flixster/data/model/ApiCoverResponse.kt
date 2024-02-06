package com.vansuita.flixster.data.model

import com.google.gson.annotations.SerializedName


data class ApiCoverResponse(
	@SerializedName("page")
	val page: Int,

	@SerializedName("results")
	val results: List<ApiCoverData>,
)