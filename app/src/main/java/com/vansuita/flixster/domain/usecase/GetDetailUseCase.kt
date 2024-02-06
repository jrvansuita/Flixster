package com.vansuita.flixster.domain.usecase

import com.vansuita.flixster.data.repository.CoverRepository
import com.vansuita.flixster.data.repository.CoverRepositoryImpl
import com.vansuita.flixster.domain.model.DataType
import com.vansuita.flixster.domain.model.Detail

class GetDetailUseCase(private val coverRepository: CoverRepository = CoverRepositoryImpl()) {

	suspend operator fun invoke(id: String, type: DataType): Detail =
		coverRepository.getDetail(id, type)
}