package com.vansuita.flixster.domain.model


data class TvShowCover(
	override val id: String,
	override val title: String,
	override val cover: String,
) : Cover

