package com.vansuita.flixster.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vansuita.flixster.domain.model.Cover
import com.vansuita.flixster.domain.model.DataType
import com.vansuita.flixster.domain.model.MovieCover
import com.vansuita.flixster.domain.model.TvShowCover
import com.vansuita.flixster.domain.usecase.GetDetailUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class DetailViewModel(
	private val getDetailUseCase: GetDetailUseCase = GetDetailUseCase(),
) : ViewModel() {

	private val _detailState: MutableLiveData<DetailState> = MutableLiveData()
	val detailState: LiveData<DetailState> = _detailState

	fun load(cover: Cover) {

		val type = when (cover) {
			is MovieCover -> DataType.Movie
			is TvShowCover -> DataType.TvShow
			else -> TODO()
		}

		val exceptionHandler = CoroutineExceptionHandler { _, e ->
			e.printStackTrace()
			//empty handler
		}

		viewModelScope.launch(exceptionHandler) {
			_detailState.value = DetailState(getDetailUseCase(cover.id, type))
		}
	}

}