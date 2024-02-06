package com.vansuita.flixster.domain.usecase

import com.vansuita.flixster.data.repository.CoverRepository
import com.vansuita.flixster.data.repository.CoverRepositoryImpl
import com.vansuita.flixster.domain.model.MovieCover

class GetMoviesCoversUseCase(private val coverRepository: CoverRepository = CoverRepositoryImpl()) {

	suspend operator fun invoke(): List<MovieCover> = coverRepository.getMoviesCovers()
}