package com.vansuita.flixster.data.model

import com.google.gson.annotations.SerializedName

data class ApiDetailResponse(
	@SerializedName("type")
	val type: String?,

	@SerializedName("genres")
	val genres: List<ApiGenreData>,

	@SerializedName("created_by")
	val authors: List<ApiCreatedByData>?,

	@SerializedName("overview")
	val overview: String
)