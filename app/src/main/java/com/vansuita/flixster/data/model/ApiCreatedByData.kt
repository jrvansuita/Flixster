package com.vansuita.flixster.data.model

import com.google.gson.annotations.SerializedName

data class ApiCreatedByData(
	@SerializedName("name")
	val name: String
)