package com.example.starwars.presentation.ui.fragments

import androidx.paging.PagingData
import com.example.starwars.domain.models.ResultModel
import com.example.starwars.domain.usecases.FetchPagedStarshipsUseCase
import com.example.starwars.domain.usecases.FetchStarshipsUseCase
import com.example.starwars.presentation.base.BaseViewModel
import com.example.starwars.presentation.models.ResultUI
import com.example.starwars.presentation.models.StarshipsUI
import com.example.starwars.presentation.models.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StarshipsViewModel @Inject constructor(
    private val starshipsUseCase: FetchStarshipsUseCase,
    private val fetchPagedStarshipsUseCase: FetchPagedStarshipsUseCase
) :
    BaseViewModel() {

    private val _starshipsState = mutableUiStateFlow<StarshipsUI>()
    val starshipsState = _starshipsState.asStateFlow()


    private fun fetchStarships() = starshipsUseCase().gatherRequest(_starshipsState) { it.toUI() }

    fun fetchPagedStarships(): Flow<PagingData<ResultUI>> {
        val pagedData = fetchPagedStarshipsUseCase() as Flow<PagingData<ResultModel>>
        return pagedData.gatherPagingRequest { it.toUI() }
    }

    init {
        fetchStarships()
        fetchPagedStarships()
    }

}