package com.vansuita.flixster.domain.model

import java.io.Serializable

data class Detail(
	val type: String,
	val genres: List<String>,
	val authors: List<String>,
	val overview: String
) : Serializable