package com.vansuita.flixster.data.model

import com.google.gson.annotations.SerializedName


data class Movie(
	@SerializedName("poster_path")
	val cover: String,

	@SerializedName("title")
	val title: String,

	@SerializedName("overview")
	val overview: String
)

