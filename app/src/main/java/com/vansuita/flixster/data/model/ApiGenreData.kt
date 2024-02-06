package com.vansuita.flixster.data.model

import com.google.gson.annotations.SerializedName

data class ApiGenreData(
	@SerializedName("name")
	val name: String
)