package com.vansuita.flixster.domain.usecase

import com.vansuita.flixster.data.repository.CoverRepository
import com.vansuita.flixster.data.repository.CoverRepositoryImpl
import com.vansuita.flixster.domain.model.TvShowCover

class GetTvShowsCoversUseCase(private val coverRepository: CoverRepository = CoverRepositoryImpl()) {

	suspend operator fun invoke(): List<TvShowCover> = coverRepository.getTvShowsCovers()
}