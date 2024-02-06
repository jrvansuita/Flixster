package com.vansuita.flixster.data.model

import com.google.gson.annotations.SerializedName


data class ApiCoverData(
	@SerializedName("id")
	val id: String,

	@SerializedName("poster_path")
	val cover: String,

	@SerializedName("title", alternate = ["name"])
	val title: String,

	@SerializedName("overview")
	val overview: String
)

