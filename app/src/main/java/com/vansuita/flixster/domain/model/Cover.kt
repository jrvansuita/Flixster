package com.vansuita.flixster.domain.model

import java.io.Serializable

interface Cover : Serializable {
	val id: String
	val title: String
	val cover: String
}